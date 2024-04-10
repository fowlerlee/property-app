import * as React from 'react';
import { SafeAreaView, Button, View, Text, ActivityIndicator, FlatList, StyleSheet, ImageComponent, TouchableOpacity, Image } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { useEffect, useContext, useRef, useReducer, useState } from 'react';
import Replicate from "replicate";
// import dotenv from "dotenv";

// const path = require('path')
// const a = dotenv.config({path: path.resolve('.env')})
// console.log("path", a);

interface StateType {
    text: string[];
}

const replicate = new Replicate({
  auth: "process.env.REPLICATE_API_TOKEN",
});

export default function LoginScreen({ route, navigation }) {
    const [isLoading, setLoading] = useState(true);
    const [text, setText] = useState<StateType>({ text: [] });
  
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
      //getAIWords();
    }, []);
  
    return (
      <SafeAreaView style={styles.container}>
        <Text>Login Screen</Text>
        <TouchableOpacity style={styles.button}>
        <Button
          title="Make request to Replicate"
          onPress={getAIWords}
        />
        <Button title="Go to Home" onPress={() => navigation.navigate('Home')} />
        <Button title="Go back" onPress={() => navigation.goBack()} />
        </TouchableOpacity>
        {isLoading ? (
          <ActivityIndicator />
        ) : (
          <Text>Current text state: {text.text.join(' ')}</Text>
        )}
      </SafeAreaView>
    );
  }

  const styles = StyleSheet.create({
    imageAI: {
      width: '50%',
      height: '50%',
    },
    container: {
      alignItems : "center",
      paddingTop: 70,
    },
    image: {
      flex: 1,
      padding: 24,
    },
    button: {
      flex: 1,
      padding: 24,
      backgroundColor: '#ffb703',
      borderRadius: 50,
    },
    list: {
      flex: 1,
      padding: 24,
      color: 'black',
      fontFamily: "Helvitica",
      fontStyle:"italic",
      fontWeight:"bold",
      alignContent: "center"
    },
    title: {
      marginTop: 16,
      paddingVertical: 8,
      borderWidth: 4,
      borderColor: '#219ebc',
      borderRadius: 6,
      backgroundColor: '#8ecae6',
      color: '#023047',
      textAlign: 'center',
      fontSize: 30,
      fontWeight: 'bold',
    },
  });