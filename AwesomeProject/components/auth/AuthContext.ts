import axios from 'axios';
import urls from '../api/api.js'
import AsyncStorage from '@react-native-async-storage/async-storage';

  const user = {
      firstname: "Nelson",
      lastname: "Mandela",
      email: "nelson@gmail.com",
      "password": "1234",
      role: "PERSON",
  }

  const logout = async () => {
    await AsyncStorage.removeItem("jwtToken")
  }

  const register = async (firstname: string, lastname: string, email:string, password:string) => {

    return axios.post(urls.registerUrl, {
      firstname,
      lastname,
      email,
      password,
    });
  }

  const getCurrentUser = async () =>  {
    let userData = null;
    try {
        userData = JSON.stringify(await AsyncStorage.getItem("jwtToken"))
    } catch (error) {
        console.log("error message: ", error)
    }
    return userData;
  }

  const login = async (email, password) => {
      return axios
        .post(urls.authUrl, {
          email,
          password
        })
        .then(response => {
          if (response.data.accessToken) {
            AsyncStorage.setItem("jwtToken", JSON.stringify(response.data));
          }
          return response.data;
        });
  }

export {login, logout, getCurrentUser, register};

