import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import { pink } from '@mui/material/colors';
import { useState } from "react";
import axios from "axios";
import validator from "validator";
import Avatar from '@mui/material/Avatar';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import {IconFlagTR} from 'material-ui-flags';
import Alert from '@mui/material/Alert';
import CheckIcon from '@mui/icons-material/Check';

export default function SignUp() {
  var phoneIsError= false;
  var incomeIsError = false;
  var usernameIsError = false;
  var passwordIsError = false;
  var idNumberIsError = false;
  var firstNameIsError = false;
  var lastNameIsError= false;
  var creditScoreIsError=false;
  var birthDateIsError = false;
  var convertedDate ;
  const [serverError,setServerError] = useState ([false,""]);

  const [phoneErrorHolder,setPhoneErrorHolder] = useState ([false,""]);
  const [incomeErrorHolder,setIncomeErrorHolder] = useState ([false,""]);
  const [usernameErrorHolder,setUsernameErrorHolder] = useState([false,""]);
  const [passwordErrorHolder,setPasswordErrorHolder] = useState([false,""]);
  const [idNumberErrorHolder,setIdNumberErrorHolder] = useState([false,""]);
  const [firstNameErrorHolder,setFirstNameErrorHolder] = useState([false,""]);
  const [lastNameErrorHolder,setLastNameErrorHolder] = useState([false,""]);
  const [creditScoreErrorHolder,setCreditScoreErrorHolder] = useState([false,""]);
  const [birthDateErrorHolder,setBirthDateErrorHolder] = useState([false,""]);
    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
      console.log("handleOpen");
        setOpen(true);
    };

    const handleClose = () => {
      console.log("handleClose");
        setOpen(false);
    };

    const handleSubmit = (event) => {
    
        const data = new FormData(event.currentTarget);
        event.preventDefault();
    
        if(validator.isNumeric(data.get('income'))){
            incomeIsError = false;
            setIncomeErrorHolder([false,""]);
    
        }else{
            incomeIsError = true;
            setIncomeErrorHolder([true,"Income is required"]);
    
           
        }
    
        if(validator.isMobilePhone("+90"+data.get('phoneNumber'), "tr-TR")){
            phoneIsError=false;
            setPhoneErrorHolder([false,""]);
        }else{
            phoneIsError=true;
            setPhoneErrorHolder([true,"must be like 554 XXX XX XX"]);
                    
        }
    
        if(!validator.isEmpty(data.get('username'))){
            usernameIsError=false;
            setUsernameErrorHolder([false,""])
        }else{
            usernameIsError=true;
            setUsernameErrorHolder([true,"Username is required"])
          
        }
    
        if(!validator.isEmpty(data.get('password'))){
           passwordIsError=false;
           setPasswordErrorHolder([false,""])
        }else{
            passwordIsError=true;
            setPasswordErrorHolder([true,"Password is required"])
            
        }
    
        if(!validator.isEmpty(data.get('lastName'))){
            lastNameIsError=false;
            setLastNameErrorHolder([false,""])
        }else{
            lastNameIsError=true;
            setLastNameErrorHolder([true,"Last name is required"])
            
        }
    
        if(!validator.isEmpty(data.get('firstName'))){
            firstNameIsError=false;
            setFirstNameErrorHolder([false,""])  
        }else{
            firstNameIsError=true;
            setFirstNameErrorHolder([true,"First name is required"])
            
        }
    
        if(data.get('idNumber').length===11){
            idNumberIsError=false;
            setIdNumberErrorHolder([false,""])
        }else{
            idNumberIsError=true;
            setIdNumberErrorHolder([true,"11 digit must be entered"])
            
        }

        if(!validator.isEmpty(data.get('creditScore'))){
          creditScoreIsError=false;
          setCreditScoreErrorHolder([false,""])
        }else{
        creditScoreIsError=true;
        setCreditScoreErrorHolder([true,"enter value"])
          
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
    
        if(!phoneIsError && !incomeIsError && !usernameIsError && !passwordIsError && !idNumberIsError && !firstNameIsError && !lastNameIsError && !creditScoreIsError && !birthDateIsError){
          const { response } = axios({
            method: 'post',
            url:  `http://localhost:2023/api/v1/auth/register`,
            headers: { 
              'Content-Type': 'application/json'
            },
            data:
             {
              firstName: data.get('firstName'),
              lastName: data.get('lastName'),
              username: data.get('username'),
              password: data.get('password'),
              idNumber: data.get('idNumber'),
              phoneNumber: `+90${data.get('phoneNumber')}`,
              income: data.get('income'),
              creditScore: data.get('creditScore'),
              birthDate: convertedDate
            }
        }).then(response=>{
          
          if(response.status = 200){
            handleClose();
            window.location.reload(true);
          }
    
        }).catch(error=>{
          if(error){
            
            if(error.response){
              if(error.response.request){
                console.log(error.response.status);
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
            <div>
                <Button onClick={handleClickOpen} sx={{ color: pink[500], cursor: "pointer" }} >Don't have an account? Sign Up</Button> 
                <Dialog maxWidth='xl' open={open} onClose={handleClose}>
                    <DialogContent>
                        
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
          
          {/* <div className='red'><p color='red'>{serverError[1]}</p></div> */}
          <Typography component="h1" variant="h5">
            Sign up
          </Typography>
          <Box
            component="form"
            noValidate
            onSubmit={handleSubmit}
            sx={{ mt: 3 }}
          >
            <Grid container spacing={2}>
              <Grid item xs={12} sm={6}>
                <TextField
                  autoComplete="given-name"
                  name="firstName"
                  required
                  fullWidth
                  type='text'
                  id="firstName"
                  label="First Name"
                  autoFocus
                  error={firstNameErrorHolder[0]}
                  helperText={firstNameErrorHolder[1]}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  required
                  fullWidth
                  type="text"
                  id="lastName"
                  label="Last Name"
                  name="lastName"
                  autoComplete="family-name"
                  error={lastNameErrorHolder[0]}
                  helperText={lastNameErrorHolder[1]}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  type='text'
                  id="username"
                  label="Username"
                  name="username"
                  autoComplete="username"
                  error={usernameErrorHolder[0]}
                  helperText={usernameErrorHolder[1]}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  autoComplete="new-password"
                  error={passwordErrorHolder[0]}
                  helperText={passwordErrorHolder[1]}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="idNumber"
                  label="Id Number"
                  type="number"
                  id="idNumber"
                  autoComplete="idNumber"
                  error={idNumberErrorHolder[0]}
                  helperText={idNumberErrorHolder[1]}
                />
              </Grid>
              <Grid item xs={12}>
              <div><IconFlagTR />  TR +90 already added.Enter without 0 </div><TextField
                  required
                  fullWidth
                  name="phoneNumber"
                  label="Phone Number"
                  type= 'number'
                  id="phoneNumber"
                  autoComplete="phoneNumber"
                  error={phoneErrorHolder[0]}
                  helperText={phoneErrorHolder[1]}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="income"
                  label="Income(Monthly)"
                  type="number"
                  id="income"
                  autoComplete="income"
                  error={incomeErrorHolder[0]}
                  helperText={incomeErrorHolder[1]}

                  
               
                />
              </Grid>
              <Grid item xs={12}>
              <TextField
                  required
                  fullWidth
                  name="creditScore"
                  label="Credit Score"
                  type= 'number'
                  id="creditScore"
                  autoComplete="creditScore"
                  error={creditScoreErrorHolder[0]}
                  helperText={creditScoreErrorHolder[1]}
                />
              </Grid>
              <Grid item xs={12}>
              <TextField
                  name="birthDate"
                  required
                  fullWidth
                  type='Date'
                  id="birthDate"
                  error={birthDateErrorHolder[0]}
                  helperText={"Birth Date"}
                  // autoFocus
                />
                
              </Grid>
              
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign Up
            </Button>
            {serverError[0] ? <Alert icon={<CheckIcon fontSize="inherit" />} severity="error">
        `{serverError[1]}`
      </Alert> : null}
            <Grid container justifyContent="flex-end">
              <Grid item>
                 </Grid>
               </Grid>
              </Box>
           </Box>
              </Container>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose}>Cancel</Button>
                    </DialogActions>
                </Dialog>
            </div>
            );
        
}