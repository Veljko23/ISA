import React from "react";
import { useNavigate } from "react-router-dom";
import "../css/form.css";

const AdminHomePage = () => {
  const navigate = useNavigate();
  const onUsersHistoryClickHandler = (event) => {
    return navigate("/users");
  };
  const onGraphPassengerClickHandler = (event) => {
    return navigate("/graphPassengers");
  };
  const onGraphDriversClickHandler = (event) => {
    return navigate("/graphDrivers");
  };
  const onEditProfileClickHandler = (event) => {
    const userId = localStorage.getItem("userId");
    return navigate(`/edit-profile/${userId}`);
  };
  const onCreateDriverClickHandler = (event) => {
    const userId = localStorage.getItem("userId");
    const role = localStorage.getItem("role");
    return navigate("/createDriver");
  };

  const onRequestsClickHandler = (event) => {
    return navigate("/requests");
  };

  const logoutClickHandler = () => {
    localStorage.clear()
    navigate('/')
  }

  const userRole = localStorage.getItem("role");
  if(userRole !== "ROLE_ADMIN"){
    return <div>You can't access this page! Back to <a href="/"><button>Login</button></a></div>;
  }

  return (
    <div className="registration-form-container">
      <div className="registration-form-wrapper">
          <div className="button-group">
            <button className="item" type="submit" onClick={onUsersHistoryClickHandler}>
              Users history
            </button>
            <button type="button" onClick={onGraphPassengerClickHandler}>
              Graphs for passengers
            </button>
            <button type="button" onClick={onGraphDriversClickHandler}>
              Graphs for drivers
            </button>
            <button type="button" onClick={onEditProfileClickHandler}>
              Edit profile
            </button>
            <button type="button" onClick={onCreateDriverClickHandler}>
              Create driver profile
            </button>
            <button type="button" onClick={onRequestsClickHandler}>
              Requests for profile change
            </button>
            <button type="button" onClick={logoutClickHandler}>
              Logout
            </button>
          </div>
      </div>
    </div>
  );
};

export default AdminHomePage;
