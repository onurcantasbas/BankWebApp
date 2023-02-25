import * as React from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import axios from "axios";
import Alert from '@mui/material/Alert';
import CheckIcon from '@mui/icons-material/Check';
import { useState,useEffect } from "react";



export default function LoanApplication(){

  const [isSuccess,setIsSuccess] = useState(false);
  const [serverErrorMessage,setServerErrorMessage] = useState([false,""]);

  useEffect(() => {
    // Update the document title using the browser API
    setServerErrorMessage([false,""]);
  },[localStorage.getItem('token')]);

    const handleSubmit = (event) => {
    
        const data = new FormData(event.currentTarget);
        event.preventDefault();
    
        
         const { response } = axios({
            
            method: 'post',
            url:  `http://localhost:2023/api/v1/credit/apply`,
            withCredentials: false,
            headers: { 
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${localStorage.getItem('token')}`,
              "Access-Control-Allow-Origin": "*",
                    "Access-Control-Allow-Methods": "GET, POST, PATCH, PUT, DELETE, OPTIONS",
                    "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token, Authorization, Accept,charset,boundary,Content-Length"  
            },data:
            {
              token : localStorage.getItem('token'),
              guarantee: data.get('guarantee'),
            }
        }).then(response=>{
          
          if(response.status = 200){
            setIsSuccess(true);
            setServerErrorMessage(false,"");
          }
    
        }).catch(error=>{
          if(error){
            console.log(error);
            if(error.response){
              if(error.response.status<500){
                if(error.response.request.response){
                  var errorMessage = error.response.request.response;
                  var errorMessage1 = errorMessage.split(',')[2];
                  var errorMessage2 = errorMessage1.split(':')[1];
                  // console.log(error);
                  setServerErrorMessage([true,errorMessage2]);             
                }  
              }else{
                localStorage.setItem('token',' ');
                setServerErrorMessage([false,""]);
                window.location.reload(true);

              }
              // if(error.response.request){
              //   error.response.status
              // }
            }
          }         
        });
      };
    
    return(

    
        <div>
        <Container component="main" maxWidth="xs">
        {/* {serverErrorMessage[0] ? <Alert icon={<CheckIcon fontSize="inherit" />} severity="error">
        `{serverErrorMessage[1]}`
      </Alert> : null} */}
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Typography component="h1" variant="h5">
            Your credit limit will be sent to your phone number.
            {isSuccess ? <Alert icon={<CheckIcon fontSize="inherit" />} severity="success">
        Success message : Check your phone for result.
      </Alert> : null}
      {serverErrorMessage[0] ? <Alert icon={<CheckIcon fontSize="inherit" />} severity="error">
        {serverErrorMessage[1]}
      </Alert> : null}

          </Typography>
          <Box
            component="form"
            noValidate
            onSubmit={handleSubmit}
            sx={{ mt: 3 }}
          >
            <Grid container spacing={2}>
              <Grid item xs={12} sm={12}>
                <TextField
                  name="guarantee"
                  fullWidth
                  type='number'
                  id="guarantee"
                  label="Guarantee"
                  autoFocus
                />
              </Grid>
            </Grid>
            
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              
              Apply Credit
            </Button>
            <Grid container justifyContent="flex-end">
              <Grid item>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
      

        </div>
    )



};