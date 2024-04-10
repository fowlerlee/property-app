import * as React from 'react';
import { Button, View, Text, ActivityIndicator, FlatList, StyleSheet, ImageComponent, TouchableOpacity, Image } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { useEffect, useContext, useRef, useReducer, useState } from 'react';
import AuthForm from './components/Auth';
import Home from './components/Home';
import Intelligence from './components/Intelligence';
import Details from './components/Details';
import MPeg from './components/MPeg';

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home" component={Home} />
        <Stack.Screen name="Details" component={Details} />
        <Stack.Screen name="Intelligence" component={Intelligence} />
        <Stack.Screen name="AuthForm" component={AuthForm} />
        <Stack.Screen name="MPeg" component={MPeg} />

      </Stack.Navigator>
    </NavigationContainer>
  );
}

const styles = StyleSheet.create({
  imageAI: {
    width: '50%',
    height: '50%',
  },
  container: {
    flex: 1,
    padding: 24,
    backgroundColor: '#fb8500',
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
