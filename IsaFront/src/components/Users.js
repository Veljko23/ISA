import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../css/table.css";

const Users = (props) => {
  const token = localStorage.getItem("token");
  const [users, setUsers] = useState([]);
 
  const navigate = useNavigate();

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    
    const response = await fetch(
      "http://localhost:8080/api/users",
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
          Authorization: token,
        },
      }
    );
    if (response.status === 400 || response.status === 401) {
      return window.alert("invalid fetch!");
    }
    const data = await response.json();
   
    setUsers(data);
  };

  const handleUserClick = (user) => {
    localStorage.setItem("userId", user.id);
    navigate("/selectedUserHistory");
  };

  return (
    <div>
      <div className="game-history-container">
        <div className="game-history-table-container">
          <table className="game-history-table">
            <thead>
              <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Picture</th>
                <th>Number</th>
                <th>Email</th>
                <th>Address</th>

              </tr>
            </thead>
            <tbody>
              {users.map((user) => (
                <tr onClick={() => handleUserClick(user)} key={user.id} >
                  <td>{user.name}</td>
                  <td>{user.surname}</td>
                  <td>{user.picture}</td>
                  <td>{user.number}</td>
                  <td>{user.email}</td>
                  <td>{user.address}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default Users;