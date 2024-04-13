import * as React from 'react';
import { ImageBackground, Button, TextInput, StatusBar, SafeAreaView, View, Text, ActivityIndicator, FlatList, StyleSheet, ImageComponent, TouchableOpacity, Image } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { useEffect, useContext, useRef, useReducer, useState } from 'react';
import { Alert } from 'react-native';

import colors from './config/colors';
import sizes from './config/sizes';

const BASE_URL = 'http://localhost:8080/api/v1/person';

const input = React.createRef();

type Movie = {
    id: string;
    title: string;
    releaseYear: string;
};

type Persons = {
  id: string;
  name: string;
};

type Name = {
  name: string
}

export default function DetailsScreen({ route, navigation }) {
    const [isLoading, setLoading] = useState(true);
    const [data, setData] = useState<Movie[]>([]);
    const [persons, setPersons] = useState<Persons[]>([]);
    const [name, setName] = useState<Name>();
    const [text, onChangeText] = useState('');
    const [email, onChangeEmail] = useState('');
    const [pictures, setPictures] = useState('');
  
    const getMovies = async () => {
      try {
        const response = await fetch('https://reactnative.dev/movies.json');
        const json = await response.json();
        setData(json.movies);
        console.log("data: ", json)
      } catch (error) {
        console.error(error);
      } finally {
        setLoading(false);
      }
    };

    const getPictures = async () => {
      try {
        const response = await fetch('http://flee01.pythonanywhere.com');
        const json = await response.text();
        setPictures(json);
        console.log("pictures: ", json)
      } catch (error) {
        console.error(error);
      } finally {
        setLoading(false);
      }
    };

    const getPersons = async () => {
      try {
        const response = await fetch(BASE_URL);
        const json = await response.json();
        setPersons(json);
        console.log("persons: ", json)
        console.log("raw persons: ", json)
      } catch (error) {
        console.error(error);
      } finally {
        setLoading(false);
      }
    };

    const postOptions = { 
        method: 'POST', 
        headers: { 'Content-Type': 'application/json' }, 
        body: JSON.stringify({ id: '94ddc6cf-45dd-4bea-a9ab-0e6123ffde3e', name: text}) 
    };

    const postPerson = async () => {
      try {
         await fetch(BASE_URL, postOptions)
            .then(response => {
              response.json()
                .then(data => {
                  Alert.alert("Person created: ", data.name)
                });
            })

      } catch (error) {
        console.error(error)
      } finally {
        setLoading(false)
      }
    };
  
    useEffect(() => {
    }, []);
  
    console.log("text: ", text)
    return (
    <ImageBackground
            style={styles.background}
            source={require("../assets/penthouseview.jpg")}
      >
      <SafeAreaView style={styles.container}>
      <View style={styles.formArea}>
        <Text style={styles.title}>Enter Details</Text>
        <View style={styles.textInputArea}>
          <TextInput
            style={styles.input}
            onChangeText={onChangeText}
            value={text}
            placeholder='name'
          />
          <TextInput
            style={styles.input}
            onChangeText={onChangeEmail}
            value={email}
            placeholder='email'
          />
        </View> 
      </View>
        <View style={styles.button}>
          <Button  title="Create User" onPress={() => postPerson()} /> 
          <Text>Python everywhere: {pictures}</Text>
          <Button  title="Get Pics" onPress={() => getPictures()} />
        </View>

        {isLoading ? (
          <ActivityIndicator size="small" color="#0000ff"/>
        ) : (
          <View style={styles.containerNew}>
          <FlatList
            style={styles.list}
            data={data}
            keyExtractor={({id}) => id}
            renderItem={({item}) => (
              <Text>
                {item.title}, {item.releaseYear}
              </Text>
            )}
          />
          <View style={styles.containerNew}>
            <FlatList
            data={persons}
            style={styles.list}
            keyExtractor={({id}) => id}
            renderItem={({item}) => (
              <Text>
                {item.name}: ({item.id})
              </Text>
            )}
          />
          </View>
          </View>
        )}
      </SafeAreaView>
      </ImageBackground>
    );
  }
  
const styles = StyleSheet.create({
  background: {
      flex: 1,
      justifyContent: 'center',
      alignItems: 'center'
  },
  container : {
    alignItems : "center",
    justifyContent: 'center',
    flex: 1,
  },
  title: {
      padding: 4,
      borderWidth: 1,
      borderColor: colors.primary,
      borderRadius: 6,
      backgroundColor: colors.secondary,
      color: colors.tertiaryOne,
      textAlign: 'center',
      fontSize: sizes.title,
      fontWeight: 'bold',
      opacity: 0.5
  },
  textInputArea : {
    flexDirection: 'row',
    width: '80%',
    alignContent: 'center',
    alignItems : "center",
    justifyContent: 'space-between',
    flex: 1,
  },
  input: {
    height: 40,
    width: 150,
    margin: 12,
    borderWidth: 1,
    padding: 10,
    backgroundColor: colors.secondary,
    opacity: 0.5
  },
  formArea: {
    flex: 1,
    flexDirection: 'column',
    height: 300,
    width: '90%'
    },
    image: {
      flex: 1,
      padding: 24,
    },
    buttonText: {
      color: colors.secondary,
      fontSize: 16,
    },
    button: {
      flexDirection: 'row',
      flex: 1,
      justifyContent: 'space-between',
      alignItems: 'center',
      borderRadius: 50,
    },
    list: {
      flex: 1,
      padding: 24,
      color: 'black',
      fontStyle:"italic",
      fontWeight:"bold",
      alignContent: "center"
    },
  }); 