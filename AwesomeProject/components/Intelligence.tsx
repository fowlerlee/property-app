import * as React from 'react';
import { ImageBackground, SafeAreaView, Button, View, Text, ActivityIndicator, FlatList, StyleSheet, ImageComponent, TouchableOpacity, Image } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { useEffect, useContext, useRef, useReducer, useState } from 'react';
import Replicate from "replicate";
import Animated, {
  useAnimatedStyle,
  useSharedValue,
  withDecay,
} from 'react-native-reanimated'
import {
  Gesture,
  GestureDetector,
  GestureHandlerRootView,
} from 'react-native-gesture-handler';

import colors from './config/colors';
import sizes from './config/sizes';

interface StateType {
    text: string[];
}

const replicate = new Replicate({
  auth: "process.env.REPLICATE_API_TOKEN",
});

const SIZE = 120;

export default function LoginScreen({ route, navigation }) {
    const [isLoading, setLoading] = useState(true);
    const [text, setText] = useState<StateType>({ text: [] });
    const offset = useSharedValue(0);
    const width = useSharedValue(0);

const onLayout = (event) => {
    width.value = event.nativeEvent.layout.width;
  };

const pan = Gesture.Pan()
  .onChange((event) => {
    offset.value += event.changeX;
  })
  .onFinalize((event) => {
    offset.value = withDecay({
      velocity: event.velocityX,
      rubberBandEffect: true,
      clamp: [-(width.value / 2) + SIZE / 2, width.value / 2 - SIZE / 2],
    });
  });

  const animatedStyles = useAnimatedStyle(() => ({
    transform: [{ translateX: offset.value }],
  }));


    const getAIWords = async () => {
      try {
        const output = await replicate.run(
          "replicate/llama-13b-lora:4baede730d6bc13396e6dec0df5172bff658c014da9552bc17decfd6453d368c",
          {
            input: {
              debug: false,
              top_p: 1,
              prompt: "Simply put, the theory of relativity states that",
              max_length: 500,
              temperature: 0.75,
              repetition_penalty: 1
            }
          }
        );
        console.log(output);
        setText({ ...text, text: output });

      } catch (error) {
        console.error(error);
      } finally {
        setLoading(false);
      }
    };
  
    useEffect(() => {
    }, []);
  
    return (
    <ImageBackground
            style={styles.background}
            source={require("../assets/volcano.jpg")}
      >
      <SafeAreaView style={styles.container}>
        <Text style={styles.title}>AI Chat Help</Text>
    <GestureHandlerRootView style={styles.gesturecontainer}>
      <View onLayout={onLayout} style={styles.wrapper}>
        <GestureDetector gesture={pan}>
          <Animated.View style={[styles.box, animatedStyles]} />
        </GestureDetector>
      </View>
    </GestureHandlerRootView>

      </SafeAreaView>
      </ImageBackground>
    );
  }

  const styles = StyleSheet.create({
  gesturecontainer: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    height: '100%',
  },
  wrapper: {
    flex: 1,
    width: '100%',
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    height: SIZE,
    width: SIZE,
    backgroundColor: colors.primary,
    borderRadius: SIZE/2,
    // cursor: 'grab',
    alignItems: 'center',
    justifyContent: 'center',
  },
    background: {
      flex: 1,
      justifyContent: 'center',
      alignItems: 'center'
    },
    container: {
      alignItems : "center",
      paddingTop: 70,
    },
    button: {
      flex: 1,
      padding: 24,
      backgroundColor: colors.primary,
      borderRadius: 50,
    },
    title: {
      fontSize: sizes.title,
      color: colors.tertiaryOne,
      fontWeight: "bold",
      alignSelf: "center",
      textTransform: "uppercase",
      backgroundColor: colors.secondary,
      borderRadius: 20,
      padding: 10,
      opacity: 0.5
    }
  });