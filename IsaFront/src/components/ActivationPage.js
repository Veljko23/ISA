import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../css/form.css";
import { useParams } from "react-router";

import "react-datepicker/dist/react-datepicker.css";
import { useThemeProps } from "@mui/material";

const ActivationPage = (props) => {
  const token = localStorage.getItem("token");
  const params = useParams();

  useEffect(() => {
    fetchUserActivation();
  }, []);

  const navigate = useNavigate();

  const fetchUserActivation = async () => {
    const response = await fetch(
      "http://localhost:8080/api/users/activate/" + params.id,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
          Authorization: token,
        },
      }
    );
    if (response.status === 400 || response.status === 401) {
      return window.alert("Wrong username or password!");
    }
    if (response.ok) {
      //navigate('/login');
      return window.alert("Your account is successfully activated!");
    }
  };

  return <div>User successfully activated!</div>;
};

export default ActivationPage;
