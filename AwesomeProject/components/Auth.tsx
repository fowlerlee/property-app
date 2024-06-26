import React, { useState } from 'react';
import { Alert, Button, Image, Pressable, SafeAreaView, StyleSheet, Switch, Text, TextInput, View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import {register} from './auth/AuthContext'

import colors from './config/colors';
import sizes from './config/sizes';

const instagram = require("../assets/instagram.png")
const linkedin = require("../assets/linkedin.png")
const twitter = require("../assets/twitter.png")

  enum Role {
    PERSON=0,
    ADMIN=1
  }

export default function AuthForm({ navigation }) {
    const [click, setClick] = useState(false);
    const [firstname, setFirstname] =  useState("");
    const [lastname, setLastname] =  useState("");
    const [email, setEmail] =  useState("");
    const [password, setPassword] =  useState("");

const handleRegister = async () => {
    let value = await register(firstname, lastname, email, password);
   
}

// const postOptions = { 
//     method: 'POST', 
//     headers: { 'Content-Type': 'application/json' }, 
//     body: JSON.stringify({ id: '94ddc6cf-45dd-4bea-a9ab-0e6123ffde3e', name: text}) 
// };

// const postPerson = async () => {
//   try {
//       await fetch(urls., postOptions)
//         .then(response => {
//           response.json()
//             .then(data => {
//               Alert.alert("Person created: ", data.name)
//             });
//         })

//   } catch (error) {
//     console.error(error)
//   } finally {
//     setLoading(false)
//   }
// };

  return (
    <SafeAreaView style={styles.container}>
        <Text style={styles.title}>Login</Text>
        <View style={styles.inputView}>
            <TextInput style={styles.input} placeholder='FIRSTNAME' value={firstname} onChangeText={setFirstname} autoCorrect={false}
        autoCapitalize='none' />
            <TextInput style={styles.input} placeholder='LASTNAME' value={lastname} onChangeText={setLastname} autoCorrect={false}
        autoCapitalize='none' />
            <TextInput style={styles.input} placeholder='EMAIL' value={email} onChangeText={setEmail} autoCorrect={false}
        autoCapitalize='none'/>
            <TextInput style={styles.input} placeholder='PASSWORD' secureTextEntry value={password} onChangeText={setPassword} autoCorrect={false}
        autoCapitalize='none'/>
        </View>
        <View style={styles.rememberView}>
            <View style={styles.switch}>
                <Switch  value={click} onValueChange={setClick} trackColor={{true : "green" , false : "gray"}} />
                <Text style={styles.rememberText}>Remember Me</Text>
            </View>
            <View>
                <Pressable onPress={() => Alert.alert("Forget Password!")}>
                    <Text style={styles.forgetText}>Forgot Password?</Text>
                </Pressable>
            </View>
        </View>
        <View style={styles.buttonView}>
            <Pressable style={styles.button} onPress={handleRegister}>
                <Text style={styles.buttonText}>LOGIN</Text>
            </Pressable>
            <Text style={styles.optionsText}>OR LOGIN WITH</Text>
        </View>
        <View style={styles.mediaIcons}>
                <Image source={instagram} style={styles.icons}   />
                <Image source={twitter} style={styles.icons}  />
                <Image source={linkedin} style={styles.icons}  />
        </View>
        <Text style={styles.footerText}>Don't Have Account?<Text style={styles.signup}>  Sign Up</Text></Text>
    </SafeAreaView>
  )
}

const styles = StyleSheet.create({
  container : {
    alignItems : "center",
    paddingTop: 70,
  },
  image : {
    height : 160,
    width : 170
  },
  title : {
    fontSize : sizes.title,
    fontWeight : "bold",
    textTransform : "uppercase",
    textAlign: "center",
    paddingVertical : 40,
    color : colors.primary
  },
  inputView : {
    gap : 15,
    width : "100%",
    paddingHorizontal : 40,
    marginBottom: 5
  },
  input : {
    height : 50,
    paddingHorizontal : 20,
    borderColor : colors.primary,
    borderWidth : 1,
    borderRadius: 7
  },
  rememberView : {
    width : "100%",
    paddingHorizontal : 50,
    justifyContent: "space-between",
    alignItems : "center",
    flexDirection : "row",
    marginBottom : 8
  },
  switch :{
    flexDirection : "row",
    gap : 1,
    justifyContent : "center",
    alignItems : "center"
  },
  rememberText : {
    fontSize: 13
  },
  forgetText : {
    fontSize : 11,
    color : colors.primary
  },
  button : {
    backgroundColor : colors.primary,
    height : 45,
    borderColor : "gray",
    borderWidth  : 1,
    borderRadius : 5,
    alignItems : "center",
    justifyContent : "center"
  },
  buttonText : {
    color : colors.secondary,
    fontSize: 18,
    fontWeight : "bold"
  }, 
  buttonView :{
    width :"100%",
    paddingHorizontal : 50
  },
  optionsText : {
    textAlign : "center",
    paddingVertical : 10,
    color : "gray",
    fontSize : 13,
    marginBottom : 6
  },
  mediaIcons : {
    flexDirection : "row",
    gap : 15,
    alignItems: "center",
    justifyContent : "center",
    marginBottom : 23
  },
  icons : {
    width : 40,
    height: 40,
  },
  footerText : {
    textAlign: "center",
    color : "gray",
  },
  signup : {
    color : colors.primary,
    fontSize : 13
  }
});