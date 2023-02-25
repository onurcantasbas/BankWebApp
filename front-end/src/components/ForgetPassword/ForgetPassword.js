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
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Alert from '@mui/material/Alert';
import CheckIcon from '@mui/icons-material/Check';
import RefreshIcon from '@mui/icons-material/Refresh';

export default function ForgetPassword() {
  var usernameIsError = false;
  var idNumberIsError = false;
  var newPasswordIsError = false;
  const [serverError,setServerError] = useState ([false,""]);
  const [usernameErrorHolder,setUsernameErrorHolder] = useState([false,""]);
  const [idNumberErrorHolder,setIdNumberErrorHolder] = useState([false,""]);
  const [newPasswordErrorHolder,setNewPasswordErrorHolder] = useState([false,""]);
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
    
    
        if(!validator.isEmpty(data.get('username'))){
            usernameIsError=false;
            setUsernameErrorHolder([false,""])
        }else{
            usernameIsError=true;
            setUsernameErrorHolder([true,"Username is required"])
        } 

        if(data.get('idNumber').length===11){
            idNumberIsError=false;
            setIdNumberErrorHolder([false,""])
        }else{
            idNumberIsError=true;
            setIdNumberErrorHolder([true,"11 digit must be entered"])
        }

        if(!validator.isEmpty(data.get('newPassword'))){
            newPasswordIsError=false;
            setNewPasswordErrorHolder([false,""])
        }else{
            newPasswordIsError=true;
            setNewPasswordErrorHolder([true,"new password is required"])            
        }
    
        if(!usernameIsError && !idNumberIsError && !newPasswordIsError){
          const { response } = axios({
            method: 'put',
            url:  `http://localhost:2023/api/v1/user/resetpassword`,
            headers: { 
              'Content-Type': 'application/json'
            },
            data:
             {
              username: data.get('username'),
              idNumber: data.get('idNumber'),
              newPassword: data.get('newPassword'),
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
                <Button onClick={handleClickOpen} sx={{ color: pink[500], cursor: "pointer" }} >Forgot Password?</Button> 
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
          
            <RefreshIcon />
          </Avatar>
          
          {/* <div className='red'><p color='red'>{serverError[1]}</p></div> */}
          <Typography component="h1" variant="h5">
            Reset Password
          </Typography>
          <Box
            component="form"
            noValidate
            onSubmit={handleSubmit}
            sx={{ mt: 3 }}
          >
            <Grid container spacing={2}>
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
                <TextField
                  required
                  fullWidth
                  name="newPassword"
                  label="New Password"
                  type="password"
                  id="newPassword"
                  autoComplete="newPassword"
                  error={newPasswordErrorHolder[0]}
                  helperText={newPasswordErrorHolder[1]}
                />
              </Grid>         
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Reset Password
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