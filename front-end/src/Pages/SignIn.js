import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import validator from 'validator';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import axios from "axios";
import { useState } from "react";
import Alert from '@mui/material/Alert';
import CheckIcon from '@mui/icons-material/Check';


export default function SignIn() {
  var usernameIsError = false;
  var passwordIsError = false;
  const [serverError,setServerError] = useState ([false,""]);
  const [usernameErrorHolder,setUsernameErrorHolder] = useState([false,""]);
  const [passwordErrorHolder,setPasswordErrorHolder] = useState([false,""]);
    

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email: data.get('email'),
      password: data.get('password'),
    });
    if(!validator.isEmpty(data.get('username'))){
      usernameIsError=false;
      setUsernameErrorHolder([false,"username is required"])
  }else{
      usernameIsError=true;
      setUsernameErrorHolder([true,""])
    
  }

  if(!validator.isEmpty(data.get('password'))){
     passwordIsError=false;
     setPasswordErrorHolder([false,""])
  }else{
      passwordIsError=true;
      setPasswordErrorHolder([true,"password is required"])
      
  }
  if(!usernameIsError && !passwordIsError){
    const { response } = axios({
      method: 'post',
      url:  `http://localhost:2023/api/v1/auth/authenticate`,
      headers: { 
        'Content-Type': 'application/json'
      },
      data:
       {
        username: data.get('username'),
        password: data.get('password'),
      }
  }).then(response=>{
    
    if(response.status = 200){
      localStorage.setItem('token',response.data.token);
      localStorage.setItem('idNumber',response.data.idNumber);
      console.log(localStorage.getItem('token'));
      window.location.reload(true);
      
    }

  }).catch(error=>{
    if(error){
      if(error.response){
        if(error.response.request){
        var errorMessage = error.response.request.response;
        var errorMessage1 = errorMessage.split(',')[2];
        var errorMessage2 = errorMessage1.split(':')[1];
        console.log(errorMessage2);
        setServerError([true,errorMessage2]);
        }
      }
    }
    
  });
  }


  };

  return (

      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          {serverError[0] ? <Alert icon={<CheckIcon fontSize="inherit" />} severity="error">
        `{serverError[1]}`
      </Alert> : null}
          <Typography component="h1" variant="h5">
            Sign in
          </Typography>
          <Box
            component="form"
            onSubmit={handleSubmit}
            noValidate
            sx={{ mt: 1 }}
          >
            <TextField
              margin="normal"
              required
              fullWidth
              id="username"
              label="Username"
              name="username"
              autoComplete="username"
              autoFocus
              error={usernameErrorHolder[0]}
              helperText={usernameErrorHolder[1]}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
              error={passwordErrorHolder[0]}
              helperText={passwordErrorHolder[1]}
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign In
            </Button>
            <Grid container>
              <Grid item>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
  );
}
