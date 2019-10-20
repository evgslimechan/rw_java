
 function validate(input){
     return input.value.match("/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/g");
 }
 function check(input){
     if(!validate(input)){
         input.style.borderColor = "red";
     }else{
        input.style.borderColor = "green";
     }
 }