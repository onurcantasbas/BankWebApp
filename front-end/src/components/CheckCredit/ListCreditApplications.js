import * as React from 'react';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import Button from '@mui/material/Button';
export default function ListCreditApplications(responseData,openPop){


   const [popStatus,setPopStatus] = React.useState(openPop);
   const [clearData,setClearData] = React.useState(responseData.responseData);
    function handleClosePop (){
        setPopStatus(false);
        window.location.reload(true);
      };

      if(clearData.length !==0){

        return(

            <div>
                <Dialog maxWidth='xl' open={popStatus} onClose={handleClosePop}>
                    <DialogContent sx={{text:"bold",marginBottom:0}}>
                    {clearData.map(element =>(
        
                            <div sx={{marginLeft:10}}>
                                
                                 Credit limit:{ element.creditLimit}<br></br>
                                 Guarantee   :{ element.guarantee}<br></br>
                                 Credit Application Date: { element.createdDate}<br></br>
                                 Id Number :{ element.customerIdNumber}<br></br>
        
                            
                                 <DialogContent sx={{color:`${element.creditStatus ? "green":"red"}`,marginTop:0,}}>
                            Credit status :{element.creditStatus ? `Success ` : `Fail`}<br></br>
                            
                            </DialogContent>
                            ____________________________________________________
                            </div>
                            
                            ))}                        
                            
        
        
        
                            </DialogContent>
        
                     
                        
                        
        
        
                            <DialogActions>
                                <Button onClick={handleClosePop}>Cancel</Button>
                            </DialogActions>
                        </Dialog>
                    </div>
        )

      }else{


      }





}