    
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

    function handleSignUp() {
        var email = document.getElementById('inputEmail').value;
        var password = document.getElementById('inputPassword').value;
        if (email.length < 4) {
          alert('Please enter an email address.');
          return;
        }
        if (password.length < 4) {
          alert('Please enter a password.');
          return;
        }
        // Sign in with email and pass.
        // [START createwithemail]
        firebase.auth().createUserWithEmailAndPassword(email, password).catch(function(error) {
          // Handle Errors here.
          var errorCode = error.code;
          var errorMessage = error.message;
          // [START_EXCLUDE]
          if (errorCode == 'auth/weak-password') {
            alert('The password is too weak.');
          } else {
            alert(errorMessage);
          }
          console.log(error);
          // [END_EXCLUDE]
        });
        // [END createwithemail]  
}

    /**
     * Sends an email verification to the user.
     */
    function sendEmailVerification() {
        // [START sendemailverification]
        firebase.auth().currentUser.sendEmailVerification().then(function() {
          // Email Verification sent!
          // [START_EXCLUDE]
          alert('Email Verification Sent!');
          // [END_EXCLUDE]
        });
        // [END sendemailverification]
      }
  