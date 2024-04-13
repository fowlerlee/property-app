import * as React from 'react';
import { ImageBackground, SafeAreaView, Button, View, Text, ActivityIndicator, FlatList, StyleSheet, ImageComponent, TouchableOpacity, Image } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { useEffect, useContext, useRef, useReducer, useState } from 'react';

import colors from './config/colors';
import sizes from './config/sizes';

export default function HomeScreen({ navigation }) {
    return (
      <ImageBackground
            style={styles.background}
            source={require("../assets/background.jpg")}
      >
      <Text style={styles.title}>Your New Home in Philippines</Text>
      <SafeAreaView style={styles.container}>
        <AppButton
          title="Register"
          onPress={() => {
            navigation.navigate('Register', {
              itemId: 86,
            });
          }}
        />
        <AppButton
          title="AI Chat"
          onPress={() => {
            navigation.navigate('AI Chat', {
              itemId: 86,
            });
          }}
        />
        <AppButton
          title="Login"
          onPress={() => {
            navigation.navigate('Login', {
              itemId: 86,
            });
          }}
        />
        <TouchableOpacity 
          style={styles.button}
          onPress={() => {
                navigation.navigate('Video', {
                  itemId: 86,
                });
              }}
          >
          <Text style={styles.buttontext}>Video</Text>
        </TouchableOpacity>
      </SafeAreaView>
      </ImageBackground>
    );
  }

const AppButton = ({ onPress, title }) => (
  <TouchableOpacity onPress={onPress} style={styles.button}>
    <Text style={styles.buttontext}>{title}</Text>
  </TouchableOpacity>
);

  const styles = StyleSheet.create({
    background: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center'
    },
    container: {
      flexDirection: 'row',
      height: 300,
      width: '100%',
      alignItems : 'flex-end',
      justifyContent : 'space-around',
      marginBottom : 23,
      position: 'absolute',
      bottom: 35,
    },
    image: {
      flex: 1,
      padding: 24,
    },
    button: {
      elevation: 8,
      borderRadius: 10,
      paddingVertical: 10,
      paddingHorizontal: 12,
      padding: 24,
      backgroundColor: colors.primary,
      color: colors.secondary,
      borderColor: colors.secondary,
      borderWidth: 1,
    },
    list: {
      flex: 1,
      padding: 24,
      color: colors.tertiaryOne,
      fontFamily: "Helvitica",
      fontStyle:"italic",
      fontWeight:"bold",
      alignContent: "center"
    },
    title: {
      borderWidth: 1,
      padding: 10,
      borderColor: colors.secondary,
      borderRadius: 6,
      backgroundColor: colors.secondary,
      color: colors.primary,
      textAlign: 'center',
      fontSize: 22,
      fontWeight: 'bold',
      position: 'absolute',
      top: 40,
      alignSelf: "center",
      textTransform: "uppercase"
    },
    buttontext: {
      fontSize: sizes.buttonText,
      color: colors.secondary,
      fontWeight: "bold",
      alignSelf: "center",
      textTransform: "uppercase"
    }
  });