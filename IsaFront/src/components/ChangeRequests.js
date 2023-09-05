import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../css/table.css";

const ChangeRequests = (props) => {
  const token = localStorage.getItem("token");
  const [requests, setRequests] = useState([]);
 
  const navigate = useNavigate();

  useEffect(() => {
    fetchRequests();
  }, []);

  const fetchRequests = async () => {
    
    const response = await fetch(
      "http://localhost:8080/api/driversChange",
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
   
    setRequests(data);
  };

  const handleAccept = async (requestId) => {

    const token = localStorage.getItem("token");
    //this.user.driverId = this.user.id;

    fetch(`http://localhost:8080/api/driversChange/accept/${requestId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
        Authorization: token,
      },
    })
      .then((response) => {
        if (response.status === 400) {
          return window.alert("update failed!");
        } else {
          window.history.back();
          return window.alert("Change request accepted!");
        }
      })
      .catch((error) => {
        console.error(error);
        window.alert("An error occurred during update.");
      });

    console.log(`Accepted request with ID: ${requestId}`);
  };

  const handleDeny = async (requestId) => {

    const token = localStorage.getItem("token");
    //this.user.driverId = this.user.id;

    fetch(`http://localhost:8080/api/driversChange/deny/${requestId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
        Authorization: token,
      },
    })
      .then((response) => {
        if (response.status === 400) {
          return window.alert("update failed!");
        } else {
          window.history.back();
          return window.alert("Change request denied!");
        }
      })
      .catch((error) => {
        console.error(error);
        window.alert("An error occurred during update.");
      });

    console.log(`Accepted request with ID: ${requestId}`);
  };

  const handleBackToMain = () => {
    navigate("/adminHomePage");
  };

  return (
    <div>
      <div>
        <div className="game-history-table-container">
            <h1>Change profile requests</h1>
            {requests.length === 0 ? (
                <div className="no-requests">
                    <h3>No requests</h3>
                    <h4>Back to <button className="back-button" onClick={handleBackToMain}>Main page</button></h4>
                </div>
            
          ) : ( 
            <div className="requests-list">
              {requests.map((request) => (
                <div key={request.id} className="request-entry">
                  <div className="request-info">
                    <h3>User: {request.name} wants to change his profile!</h3>
                  </div>
                  <div className="request-buttons">
                    <button className="accept" onClick={() => handleAccept(request.id)}>Accept</button>
                    <button className="deny" onClick={() => handleDeny(request.id)}>Deny</button>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default ChangeRequests;