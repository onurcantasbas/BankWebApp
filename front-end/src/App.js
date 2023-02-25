import React from 'react';
import './App.css';
import LoanApplication from './Pages/LoanApplication';
import { Button, Grid } from '@mui/material';
import SignIn from './Pages/SignIn';
import SignUp from './Pages/SignUp';
import CheckCredit from './components/CheckCredit/CheckCredit';
import ForgetPassword from './components/ForgetPassword/ForgetPassword';
function App() {

  const logOut = () => {
       localStorage.setItem('token',' ');

       window.location.reload(true);
  }
if(localStorage.getItem('token')===' '){
  return(
    <div >

    <Grid container spacing={1} >
      
      <Grid item xs={12} sm={4} marginLeft={25}>
      <div className="App">
      <SignIn></SignIn>
      <SignUp/>
      <ForgetPassword/>
      </div>
      </Grid >
      <Grid sm={4} marginTop={8}>
        <div className="App" >
        <CheckCredit></CheckCredit>
        </div>
      </Grid>
    </Grid>
    </div>
  )
}else{
  return (
    <Grid container spacing={1}>
      <Grid item xs={12} sm={4} marginLeft={20}>
        <div className="App">
          <LoanApplication></LoanApplication>
          <Button onClick={logOut} variant="contained" sx={{ mt: 3, mb: 2 }}>
            Log out
          </Button>
        </div>
      </Grid>
      <Grid  sm={4} marginTop={2}>
        <div className="App">
          <CheckCredit></CheckCredit>
        </div>
      </Grid>
    </Grid>
  )
}  
}
export default App;
