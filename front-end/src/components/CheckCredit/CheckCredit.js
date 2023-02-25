import * as React from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import axios from "axios";
import Alert from '@mui/material/Alert';
import { useState } from "react";
import Button from '@mui/material/Button';
import Snackbar from '@mui/material/Snackbar';
import ListCreditApplications from './ListCreditApplications';
import validator from "validator";


export default function CheckCredit(){
  const [idNumberErrorHolder,setIdNumberErrorHolder] = useState([false,""]);
  const [birthDateErrorHolder,setBirthDateErrorHolder] = useState([false,""]);
  
  const [responseData,setResponseData]= React.useState({});
  const [openPop, setOpenPop] = React.useState(false);
  const [serverErrorByCheck,setServerErrorByCheck] = React.useState("");

  var idNumberIsError = false;
  var birthDateIsError = false;
  var convertedDate ;

    const [open, setOpen] = React.useState(false);


    
  const handleClick = () => {
    setOpen(true);
  };

  const handleClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }

    setOpen(false);
  };

  const action = (

    <Alert onClose={handleClose} severity="error" sx={{ width: '100%' }}>
      {serverErrorByCheck}
    </Alert>

);

    const [isError,setIsError] = useState(false);
    const theme = createTheme();
    const handleSubmit = (event) => {

    
        const data = new FormData(event.currentTarget);
        event.preventDefault();

        if(data.get('idNumber').length===11){
          idNumberIsError=false;
          setIdNumberErrorHolder([false,""]);
        }else{
          idNumberIsError=true;
          setIdNumberErrorHolder([true,"11 digit must be entered"]);        
      }
      if(!validator.isEmpty(data.get('birthDate')) & data.get('birthDate').length === 10){
        
        
        convertedDate = `${(data.get("birthDate")).split('-')[2]}/${(data.get("birthDate")).split('-')[1]}/${(data.get("birthDate")).split('-')[0]}`
        console.log(convertedDate);
        birthDateIsError=false;
        setBirthDateErrorHolder([false,""]);
      }else{
        birthDateIsError=true;
        setBirthDateErrorHolder([true,""]);
        
    }
        
      if(!idNumberIsError & !birthDateIsError){

        const { response } = axios({
            
          method: 'get',
          url:  `http://localhost:2023/api/v1/credit/check/application?userIdNumber=${data.get('idNumber')}&userBirthDate=${convertedDate}`,
          headers: { 
            'Content-Type': 'application/json',
          }
      }).then(response=>{
        
        if(response.status = 200){
          setIsError(false);
          setResponseData(response.data);
          setOpenPop(true);

        }
  
      }).catch(error=>{
        if(error){
          if(error.response){
            if(error.response.request){
              var errorMessage = error.response.request.response;
              var errorMessage1 = errorMessage.split(',')[2];
              var errorMessage2 = errorMessage1.split(':')[1];
              setServerErrorByCheck(errorMessage2);
            }
          }
          setIsError(true);
          handleClick()
        }
        
      });

      }

          
    }  
    // React.useEffect(()=>{
    //     if(isError){

    //     }

    // },[isError])
        return(

        <ThemeProvider theme={theme}>
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
          <Typography component="h1" variant="h5">
            Check Credit Application.
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
                  name="idNumber"
                  required
                  fullWidth
                  type='number'
                  id="idNumber"
                  label="Id Number"
                  error={idNumberErrorHolder[0]}
                  helperText={idNumberErrorHolder[1]}
                  autoFocus
                />
              </Grid>
              <Grid item xs={12} sm={12}>
                <TextField
                  name="birthDate"
                  required
                  fullWidth
                  type='Date'
                  id="birthDate"
                  helperText={"Birth Date"}
                  error={birthDateErrorHolder[0]}
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
              Check Credit
            </Button>
            <Snackbar
        open={open}
        autoHideDuration={6000}
        onClose={handleClose}
        action={isError ? action : null}
      />
            <Grid container justifyContent="flex-end">
              <Grid item>
              </Grid>
            {openPop ? <ListCreditApplications responseData={responseData} openPop={openPop}/> :null}
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>


        );
        
    
        
      }



