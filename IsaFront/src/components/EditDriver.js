import React from "react";
import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "../css/table.css";
import axios from "axios";

const EditDriver = () => {
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
    documents: "",
    driverId: 0,
  });

  const navigate = useNavigate();

  useEffect(() => {
    fetchUser();
  }, []);

  const fetchUser = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/drivers/${userId}`,
        {
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
            Authorization: token,
          },
        }
      );
      const userData = response.data;
      userData.driverId = userId;
      setUser(userData);
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
    //this.user.driverId = this.user.id;

    fetch(`http://localhost:8080/api/driversChange`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
        Authorization: token,
      },
      body: JSON.stringify(user),
    })
      .then((response) => {
        if (response.status === 400) {
          return window.alert("update failed!");
        } else {
          window.history.back();
          return window.alert("Successfully update!");
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
          <input
            type="text"
            name="name"
            value={user.name}
            onChange={handleInputChange}
          />
        </label>
        <label>
          Surname:
          <input
            type="text"
            name="surname"
            value={user.surname}
            onChange={handleInputChange}
          />
        </label>
        <label>
          Address:
          <input
            type="text"
            name="address"
            value={user.address}
            onChange={handleInputChange}
          />
        </label>
        <label>
          Email:
          <input
            name="email"
            value={user.email}
            onChange={handleInputChange}
            required
          />
        </label>

        <label>
          Number:
          <input
            type="text"
            name="number"
            value={user.number}
            onChange={handleInputChange}
          />
        </label>
        <label>
          Picture:
          <input
            type="text"
            name="picture"
            value={user.picture}
            onChange={handleInputChange}
          />
        </label>
        <label>
          Documents:
          <input
            type="text"
            name="documents"
            value={user.documents}
            onChange={handleInputChange}
          />
        </label>

        

        <button type="submit">Save</button>
      </form>
      <button className="changePassword" onClick={handleChangePassword}>
        Change Password
      </button>
    </div>
  );
};

export default EditDriver;
