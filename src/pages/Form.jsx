// import React, { useState } from 'react'
// import Header from '../components/Header';

// const Form = () => {
//     const [email, setEmail] = useState("");
//     const [password, setPassword] = useState("");

//     const handleSubmit = (e) => {
//         e.preventDefault();
//         console.log(email);
//         console.log(password);

//         fetch("http:localhost:8080/api/student", {
//             method: "POST",
//             headers: {
//                 "Content-Type": "application/json",
//             },
//             body:  JSON.stringify({})   
//         })
//     }
//   return (
//     <div>
//       <Header/>
//       <h1>Login</h1>
//       <form onSubmit={handleSubmit}>
//         <div>
//             <label htmlFor="email"> Email</label>
//             <input type="email" id="email" placeholder='Type your email' onChange={(e) => setEmail(e.target.value)} />
//         </div>
//         <div>
//             <label htmlFor="password"> Email</label>
//             <input type="password" id="password" placeholder='Type your password' onChange={(e) => setPassword(e.target.value)} />
//         </div>
//         <button>Submit</button>
//       </form>
     
//     </div>
//   )
// }

// export default Form
