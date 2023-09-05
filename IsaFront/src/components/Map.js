import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { MapContainer, TileLayer, Marker, Popup, Polyline, useMapEvents } from 'react-leaflet'
import '../App.css'
import 'leaflet/dist/leaflet.css';

const Map = () => {
  const [startLocation, setStartLocation] = useState({ lat: 45.24638577871399, lng: 19.851222038269047 });
  const [endLocation, setEndLocation] = useState({ lat: 51.505, lng: -0.09 });
  const [showMarkers, setShowMarkers] = useState(false); // State to control marker visibility
  const routePoints = [
    [startLocation.lat, startLocation.lng],
    [endLocation.lat, endLocation.lng],
  ];
  const navigate = useNavigate();


  const handleSubmit = (e) => {
    e.preventDefault();
    setShowMarkers(true); 
  };


  const MapClickHandler = () => {
    useMapEvents({
      click: (e) => {
        const { lat, lng } = e.latlng;
        if (showMarkers) {
          setEndLocation({ lat, lng });
        } else {
          setStartLocation({ lat, lng });
        }
      }
    });
    return null;
  };


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
        {showMarkers && (
          <Polyline positions={routePoints} color="blue" />
        )}
      </MapContainer>

      {}
      <form onSubmit={handleSubmit}>
        <div>
          <label>
            Start Location:
            <input
              type="number"
              step="any"
              value={startLocation.lat}
              onChange={(e) => setStartLocation({ ...startLocation, lat: parseFloat(e.target.value) })}
            />
            <input
              type="number"
              step="any"
              value={startLocation.lng}
              onChange={(e) => setStartLocation({ ...startLocation, lng: parseFloat(e.target.value) })}
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
              onChange={(e) => setEndLocation({ ...endLocation, lat: parseFloat(e.target.value) })}
            />
            <input
              type="number"
              step="any"
              value={endLocation.lng}
              onChange={(e) => setEndLocation({ ...endLocation, lng: parseFloat(e.target.value) })}
            />
          </label>
        </div>
        <div>
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  );
};

export default Map;
