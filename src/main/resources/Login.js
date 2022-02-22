import './Login.css';
import { useEffect, useState } from "react";
import { useLocalState } from "../util/useLocalStorage";
import { useUser } from "../UserProvider";
import { useNavigate } from "react-router-dom";

const Login = () => {
    // const user = useUser();
    // const navigate = useNavigate();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [jwt, setJwt] = useLocalState("", "jwt");

    // useEffect(() => {
    //     if (user.jwt) navigate("/dashboard");
    //   }, [user]);

    function sendLoginRequest() {
        const reqBody = {
            username: username,
            password: password,
          };

        console.log(reqBody);

        const request = new Request("http://localhost:9067/person/login", {
            method: "post",
            body: JSON.stringify(reqBody),
            headers: {
                Accept: "application/json, text/plain, */*",
                "Content-Type": "application/json"
            }
        });

        fetch(request)
        .then((response) => {
            if (response.status === 200)
            return Promise.all([response.json(), response.headers]);
            else return Promise.reject("Incorrect username or password!");
        })
        .then(([body, headers]) => {
            setJwt(headers.get("authorization"));
            window.location.href = "/"
        })
        .catch((message) => {
            alert(message);
        });
        console.log("I'm sending a login request");
    }

    return(
    <div className="login-container">
        <div className='login'>
            <p className="sign" align="center">Login</p>
            <form className='form1'>
                <input type="text" className='username' align="center" 
                placeholder='Username' value={username} onChange={(event) => setUsername(event.target.value)} required/>
                
                <input type="password" className='password' align="center" 
                placeholder='Password' value={password} onChange={(event) => setPassword(event.target.value)} required/>
                
                <button type='submit' className='submit' onClick={() => sendLoginRequest()}>Login</button>
                
                <p className="forgot" align="center"><a href="#">Forgot Password?</a> </p><br></br>
                <p className="register" align="center">No account? <a href="#">Register here!</a></p><br></br>
                <p className="cancel" align="center"><a href="/">Cancel</a> </p><br></br>
            </form>
        </div>
    </div>
    )

}

export default Login;