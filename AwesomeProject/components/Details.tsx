import * as React from 'react';
import { Button, TextInput, StatusBar, SafeAreaView, View, Text, ActivityIndicator, FlatList, StyleSheet, ImageComponent, TouchableOpacity, Image } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { useEffect, useContext, useRef, useReducer, useState } from 'react';
import { Alert } from 'react-native';
// import { Button } from 'react-native-paper';

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
      // getMovies();
      // getPersons();
      // postPerson();
    }, []);
  
    console.log("text: ", text)
    return (
      <SafeAreaView style={styles.container}>
        <Text>Enter Name</Text>

        <View style={styles.inputView}>
          <TextInput
            style={styles.input}
            onChangeText={onChangeText}
            value={text}
          />
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
      
    );
  }
  
const styles = StyleSheet.create({
  container : {
    alignItems : "center",
    paddingTop: 70,
  },
  inputView : {
    gap : 15,
    width : "100%",
    paddingHorizontal : 40,
    marginBottom  :5
  },
  input: {
    height: 40,
    margin: 12,
    borderWidth: 1,
    padding: 10,
  },
    imageAI: {
      width: '50%',
      height: '50%',
    },
    image: {
      flex: 1,
      padding: 24,
  
    },
    buttonText: {
      color: 'white', // White text for better contrast
      fontSize: 16, // Adjust font size as needed
    },
    button: {
      justifyContent: 'center',
      alignItems: 'center',
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