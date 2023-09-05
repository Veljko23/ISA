import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Routing from "./Routing";
import '../App.css'

import {
  MapContainer,
  TileLayer,
  Marker,
  Popup,
  Polyline,
  useMapEvents,
} from "react-leaflet";
import L from "leaflet";
import "leaflet-routing-machine/dist/leaflet-routing-machine.css";
import "leaflet-routing-machine";
import "../App.css";
import "leaflet/dist/leaflet.css";

const StartPage = () => {
  const token = localStorage.getItem("token");
  const navigate = useNavigate();
  const [startLocation, setStartLocation] = useState({
    lat: 45.24638577871399,
    lng: 19.851222038269047,
  });
  const [endLocation, setEndLocation] = useState({ lat: 51.505, lng: -0.09 });
  const [showMarkers, setShowMarkers] = useState(false); // State to control marker visibility
  const [startLocationsFinished, finishStartLocation] = useState(false);
  const [endLocationsFinished, finishEndLocation] = useState(false);
  const [bothLocationsFinished, finishBothLocations] = useState(false);

  const routePoints = [
    [startLocation.lat, startLocation.lng],
    [endLocation.lat, endLocation.lng],
  ];
  const [vehicles, setVehicles] = useState([]);
  useEffect(() => {
    fetchVehicles();
  }, []);

  const fetchVehicles = async () => {
    const response = await fetch("http://localhost:8080/api/vehicles", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
        Authorization: token,
      },
    });
    if (response.status === 400 || response.status === 401) {
      return window.alert("Fetch failed!");
    }
    const data = await response.json();

    setVehicles(data);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setShowMarkers(true);
    if (startLocationsFinished && endLocationsFinished) {
      finishBothLocations(true);
    }
  };

  const onSetStart = (e) => {
    finishStartLocation(true);
  };

  const onSetEnd = (e) => {
    finishEndLocation(true);
  };

  const MapClickHandler = () => {
    useMapEvents({
      click: (e) => {
        const { lat, lng } = e.latlng;
        if (!bothLocationsFinished) {
        if (startLocationsFinished) {
          setEndLocation({ lat, lng });
        } else {
          setStartLocation({ lat, lng });
          setShowMarkers(true);
        }
      }
      },
    });
    return null;
  };

  const redMarkerIcon = new L.Icon({
    iconUrl:"https://lakelandescaperoom.com/wp-content/uploads/2019/11/google-map-marker-red-peg-png-image-red-pin-icon-png-clipart-pins-on-a-map-png-880_1360.jpg",
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
  });

  const goToLoginHandler = () =>{
      navigate("/login");
  }

  return (
    <div id="map">
      <MapContainer center={startLocation} zoom={13} scrollWheelZoom={false}>
        <TileLayer
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        <MapClickHandler />
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

        {bothLocationsFinished && (
          <Routing sourceCity={startLocation} destinationCity={endLocation} />
        )}

        {vehicles.map((vehicle) => (
          <Marker
            key={vehicle.id} // Add a unique key for each marker
            position={[vehicle.location.latitude, vehicle.location.longitude]}
            icon={redMarkerIcon}
          >
            <Popup>
              <span>
                {vehicle.active ? "vehicle is active" : "vehicle is not active"}
              </span>
            </Popup>
          </Marker>
        ))}
      </MapContainer>

      <form onSubmit={handleSubmit}>
        <div>
          <label>
            Start Location:
            <input
              type="number"
              step="any"
              value={startLocation.lat}
              onChange={(e) =>
                setStartLocation({
                  ...startLocation,
                  lat: parseFloat(e.target.value),
                })
              }
            />
            <input
              type="number"
              step="any"
              value={startLocation.lng}
              onChange={(e) =>
                setStartLocation({
                  ...startLocation,
                  lng: parseFloat(e.target.value),
                })
              }
            />
          </label>
          <button type="button" className="locationButton" onClick={onSetStart}>
            Set start
          </button>
        </div>
        <div>
          <label>
            End Location:
            <input
              className="endLocation"
              type="number"
              step="any"
              value={endLocation.lat}
              onChange={(e) =>
                setEndLocation({
                  ...endLocation,
                  lat: parseFloat(e.target.value),
                })
              }
            />
            <input
              type="number"
              step="any"
              value={endLocation.lng}
              onChange={(e) =>
                setEndLocation({
                  ...endLocation,
                  lng: parseFloat(e.target.value),
                })
              }
            />
          </label>
          <button type="button" className="locationButton" onClick={onSetEnd}>
            Set end
          </button>
        </div>
        <button type="submit">Create routes</button>
      </form>
      <p>Go to the <button onClick={goToLoginHandler}>Login page</button></p>
    </div>
  );
};

export default StartPage;