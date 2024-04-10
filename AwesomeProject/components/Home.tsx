import * as React from 'react';
import { SafeAreaView, Button, View, Text, ActivityIndicator, FlatList, StyleSheet, ImageComponent, TouchableOpacity, Image } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { useEffect, useContext, useRef, useReducer, useState } from 'react';


export default function HomeScreen({ navigation }) {
    return (
      <SafeAreaView style={styles.container}>
        <Text style={styles.title}>Home</Text>
      <View style={styles.container}>
        <Text>Home Screen</Text>
        <Button
          color="#ffb703"
          title="Go to Details"
          onPress={() => {
            navigation.navigate('Details', {
              itemId: 86,
              otherParam: 'anything you want here',
            });
          }}
        />
        <Button
          title="Go to AI"
          onPress={() => {
            navigation.navigate('Intelligence', {
              itemId: 86,
              otherParam: 'anything you want here',
            });
          }}
        />
        <Button
          title="Go to AuthForm"
          onPress={() => {
            navigation.navigate('AuthForm', {
              itemId: 86,
              otherParam: 'anything you want here',
            });
          }}
        />
        <Button
          title="Go to MPeg"
          onPress={() => {
            navigation.navigate('MPeg', {
              itemId: 86,
              otherParam: 'anything you want here',
            });
          }}
        />
      </View>
      </SafeAreaView>
    );
  }

  const styles = StyleSheet.create({
    imageAI: {
    height : 160,
    width : 170,
    },
    container: {
      alignItems : "center",
      paddingTop: 70,
      justifyContent : "center",
      marginBottom : 23
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
      width : 40,
      height: 40,
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