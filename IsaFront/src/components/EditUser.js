import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../css/table.css";
import axios from "axios";

const EditUser = () => {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");
  const role = localStorage.getItem("role");
  const [user, setUser] = useState({
    id: "",
    name: "",
    surname: "",
    address: "",
    email: "",
    password: "",
    block: false,
    number: "",
    picture: "",
  });

  const navigate = useNavigate();
    
  useEffect(() => {
    fetchUser();
  }, []);

  const fetchUser = async () => {
    
    try {
      const response = await axios.get(`http://localhost:8080/api/users/${userId}`, {
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
          Authorization: token,
        },
      });
  
      setUser(response.data);
    } catch (error) {
      console.error("Error fetching user:", error);
    }
  };
  
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUser((prevUser) => ({ ...prevUser, [name]: value }));
  };

  const handleChangePassword = () => {
    navigate("/changePassword");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem("token");

    fetch(`http://localhost:8080/api/users/${user.id}`, {
      method: "PUT",
      headers: { 
        "Content-Type": "application/json",
        Accept: "application/json",
        Authorization : token },
      body: JSON.stringify(user),
    })
      .then((response) => {
        if (response.status === 400) {
          return window.alert("update failed!");
        }
        else{
          window.history.back();
          return window.alert("Successfully update!")
        }
      })
      .catch((error) => {
        console.error(error);
        window.alert("An error occurred during update.");
      });
  };

  return (
    <div className="form-edit">
      <h1>Edit Profile</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Name:
          <input type="text" name="name" value={user.name} onChange={handleInputChange} />
        </label>
        <label>
          Surname:
          <input type="text" name="surname" value={user.surname} onChange={handleInputChange} />
        </label>
        <label>
          Address:
          <input type="text" name="address" value={user.address} onChange={handleInputChange} />
        </label>
        <label>
          Email:
          <input name="email" value={user.email} onChange={handleInputChange} required/>
        </label>
        
        <label>
          Number:
          <input type="text" name="number" value={user.number} onChange={handleInputChange} />
        </label>
        <label>
          Picture:
          <input type="text" name="picture" value={user.picture} onChange={handleInputChange} />
        </label>
        {role === "ROLE_DRIVER" &&(
            <label>
            Active:
            <input
              type="checkbox"
              name="active"
              checked={user.active}
              onChange={handleInputChange}
            />
          </label>
        )}
        <button type="submit">Save</button>
      </form>
      <button className="changePassword" onClick={handleChangePassword}>
        Change Password
      </button>
    </div>
  );
};

export default EditUser;
