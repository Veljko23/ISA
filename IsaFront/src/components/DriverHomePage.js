import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/form.css";

const DriverHomePage = () => {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const [status, setStatus] = useState("Status: active");
  const onDrivingHistoryClickHandler = (event) => {
    return navigate("/driversDrivingHistory");
  };
  const onGraphClickHandler = (event) => {
    return navigate("/graphDrivers");
  };
  const onEditProfileClickHandler = (event) => {
    const userId = localStorage.getItem("userId");
    return navigate(`/editDriver/${userId}`);
  };

  const handleStatus = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/drivers/status", {
        method: "GET",
        headers: {
          'Content-Type': 'application/json',
          Accept: "application/json",
          Authorization: token,
        },
      });
      if (response.ok) {
        const data = await response.json();
        setStatus(`Status: ${data.status}`); 
      } else {
        setStatus("Status: Inactive"); 
      }
    } catch (error) {
    }
  };

  const handleLogout = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/drivers/logout", {
        method: "GET",
        headers: {
          'Content-Type': 'application/json',
          Accept: "application/json",
          Authorization: token,
        },
      });

      if (response.ok) {
        localStorage.clear();
        window.location.href = "/";
      } else {
      }
    } catch (error) {
    }
  };
  

  return (
    <div className="registration-form-container">
      <div className="registration-form-wrapper">
          <div className="button-group">
            <button className="item" type="submit" onClick={onDrivingHistoryClickHandler}>
              Driving history
            </button>
            <button type="button" onClick={onGraphClickHandler}>
              Graphs
            </button>
            <button type="button" onClick={onEditProfileClickHandler}>
              Edit profile
            </button>
            <button type="button" onClick={handleStatus}>
            {status}
            </button>
            <button type="button" onClick={handleLogout}>
              Logout
            </button>
          </div>
      </div>
    </div>
  );
};

export default DriverHomePage;
