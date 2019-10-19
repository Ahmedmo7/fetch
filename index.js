    
firebase.auth().onAuthStateChanged(function(user) {
    if (user) {

        document.getElementById("userDiv").style.display = "initial";
        document.getElementById("logDiv").style.display = "none";

        var user  = firebase.auth().currentUser;

        if(user != null){

            var emailId = user.email;

            document.getElementById("userPara").innerHTML = "Welcome User: " + emailId;
        }

    } else {

        document.getElementById("userDiv").style.display = "none";
        document.getElementById("logDiv").style.display = "initial";
    }
  });


  function login(){ 

    var userEmail = document.getElementById("inputEmail").value;
    var userPass = document.getElementById("inputPassword").value;

    firebase.auth().signInWithEmailAndPassword(userEmail, userPass).catch(function(error) {
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;        
        
        window.alert("Error: " + errorMessage);

        // ...

      });

    
      
}

function logout(){
        firebase.auth().signOut(); 
    }