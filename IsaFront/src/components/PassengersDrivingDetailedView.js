import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet'
import '../App.css'
import 'leaflet/dist/leaflet.css';
import "leaflet-routing-machine/dist/leaflet-routing-machine.css";
import "leaflet-routing-machine";
import Routing from "./Routing";
import { useEffect } from "react";

const PassengersDrivingDetailedView = (props) => {    
  const [startLocation, setStartLocation] = useState({ lat: props.selectedDriving.departure.latitude, lng: props.selectedDriving.departure.longitude });
  const [endLocation, setEndLocation] = useState({ lat: props.selectedDriving.destination.latitude, lng: props.selectedDriving.destination.longitude });
  const [showMarkers, setShowMarkers] = useState(false); 
  const selectedDriving = props.selectedDriving;
  const navigate = useNavigate();
  const routePoints = [
    [startLocation.lat, startLocation.lng],
    [endLocation.lat, endLocation.lng],
  ];
  const token = localStorage.getItem("token");
  const [driver, setDriver] = useState({});

  
  useEffect(() => {
    fetchDriving();
  }, []);

  const fetchDriving = async () => {
    try {
    const response = await fetch(`http://localhost:8080/api/drivings/withPaths/${props.selectedDriving.id}`, {
        headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
        Authorization: token,
        },
    });
    const data = await response.json();
    setDriver(data.driver);
    } catch (error) {
    console.log("Error fetching driver:", error);
    }
};

  


  return (
    <div id="map">
      <MapContainer center={startLocation} zoom={13} scrollWheelZoom={false}>
        <TileLayer
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        <routingMachine></routingMachine>
        {showMarkers && (
          <>
            <Marker position={startLocation}>
              <Popup>Start Location</Popup>
            </Marker>
            <Marker position={endLocation}>
              <Popup>End Location</Popup>
            </Marker>
          </>
        )}
        <Routing sourceCity={startLocation} destinationCity={endLocation}/>
      </MapContainer>

      {}
      <form >
        <div>
          <label>
            Start Location:
            <input
              type="number"
              step="any"
              value={startLocation.lat}
              disabled
            />
            <input
              type="number"
              step="any"
              value={startLocation.lng}
              disabled
            />
          </label>
        </div>
        <div>
          <label>
            End Location:
            <input
              type="number"
              step="any"
              value={endLocation.lat}
              disabled
            />
            <input
              type="number"
              step="any"
              value={endLocation.lng}
              disabled
            />
          </label>
        </div>

        <div className="form-registration">
          <h1>Driver Info:</h1>
          <div>
            <label htmlFor="name">Name: {driver.name}</label>
          </div>
          <div>
            <label htmlFor="surname">Surname: {driver.surname}</label>
          </div>
      
      
        </div>
      </form>
    </div>
  );
};

export default PassengersDrivingDetailedView;
