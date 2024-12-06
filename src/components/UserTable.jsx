// import React, { useEffect, useState } from 'react'

// const UserTable = () => {
//     const [users, setUsers ] = useState([]);

//     const url = "https://jsonplaceholder.typicode.com";

//     useEffect(()=> {
//         async function getAllUsers() {
//             const response = await fetch(`${url}/users`)
//             .then((response) => response.json())
//             .then((responses) => responses)
//             .catch((error) => error);

//             if(response){
//                 console.log("Reponse date:", response);
//                 setUsers(response);
//             }
//         }
//         getAllUsers();
//     },[])

//     console.log("Users", users);

//   return (
//     <div>
//       <h1>Total number of users:{users && users.length}</h1>

//       <table>
//         <thead>
//             <tr>
//                 <td>Id</td>
//                 <td>Name</td>
//                 <td>Email</td>
//             </tr>
//         </thead>
//         <tbody>
//             {users.map((users, index))}
//         </tbody>
//       </table>
//     </div>
//   )
// }

// export default UserTable
