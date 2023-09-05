import React from "react";
import { useNavigate } from "react-router-dom";
import "../css/form.css";

const PassengerHomePage = () => {
  const navigate = useNavigate();
  
  const onDrivingHistoryClickHandler = (event) => {
    return navigate("/passengersDrivingHistory");
  };
  const onGraphClickHandler = (event) => {
    return navigate("/graphPassengers");
  };
  const onEditProfileClickHandler = (event) => {
    const userId = localStorage.getItem("userId");
    return navigate(`/edit-profile/${userId}`);
  };
  const logoutClickHandler = () => {
    localStorage.clear()
    navigate('/')
  }

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
            <button type="button" onClick={logoutClickHandler}>
              Logout
            </button>
          </div>
      </div>
    </div>
  );
};

export default PassengerHomePage;
