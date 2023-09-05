import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { MapContainer, TileLayer, Marker, Popup, Polyline, useMapEvents } from 'react-leaflet'
import axios from "axios";
import '../App.css'
import 'leaflet/dist/leaflet.css';
import "leaflet-routing-machine/dist/leaflet-routing-machine.css";
import "leaflet-routing-machine";
import Routing from "./Routing";

const DriversDrivingDetailedView = (props) => {
  const [startLocation, setStartLocation] = useState({ lat: props.selectedDriving.departure.latitude, lng: props.selectedDriving.departure.longitude });
  const [endLocation, setEndLocation] = useState({ lat: props.selectedDriving.destination.latitude, lng: props.selectedDriving.destination.longitude });
  const [showMarkers, setShowMarkers] = useState(false); 
  const selectedDriving = props.selectedDriving;
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const routePoints = [
    [startLocation.lat, startLocation.lng],
    [endLocation.lat, endLocation.lng],//control
  ];
  const [passengers, setPassengers] = useState([]);

  useEffect(() => {
    fetchDriving();
  }, []);

    const fetchDriving = async () => {
        try {
        const response = await fetch(`http://localhost:8080/api/drivings/withPathsAndPassengers/${props.selectedDriving.id}`, {
            headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
            Authorization: token,
            },
        });
        const data = await response.json();
        setPassengers(data.passengers);
        } catch (error) {
        console.log("Error fetching passengerss:", error);
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
        <div>
            Passengers Info:
            <table className="game-history-table">
            <thead>
                <tr>
                <th>Name</th>
                <th>Surname</th>
                </tr>
            </thead>
            <tbody>
                {passengers.map((passenger) => (
                    <tr key={passenger.id}>
                    <td>{passenger.name}</td>
                    <td>{passenger.surname}</td>
                    
                    </tr>
                ))}
            </tbody>
            </table>
        </div>
      </form>
    </div>
  );
};

export default DriversDrivingDetailedView;