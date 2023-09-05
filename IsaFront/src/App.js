import './App.css';
import React from "react";
import {
  Route,
  Routes,
} from "react-router-dom";
import { useState } from "react";
import Login from './components/Login';
import Registration from './components/Registration';
import PassengersDrivingHistory from './components/PassengersDrivingHistory';
import DriversDrivingHistory from './components/DriversDrivingHistory';
import Users from './components/Users';
import EditUser from './components/EditUser';
import Map from './components/Map';
import PassengersDrivingDetailedView from './components/PassengersDrivingDetailedView';
import DriversDrivingDetailedView from './components/DriversDrivingDetailedView';
import PassengerHomePage from './components/PassengerHomePage';
import DriverHomePage from './components/DriverHomePage';
import SelectedUserHistory from './components/SelectedUserHistory';
import AdminHomePage from './components/AdminHomePage';
import GraphPassengers from './components/GraphPassengers';
import GraphDrivers from './components/GraphDrivers';
import ChangePassword from './components/ChangePassword';
import ForgotPassword from './components/ForgotPassword';
import StartPage from './components/StartPage';
import EditDriver from './components/EditDriver'
import CreateDriver from './components/CreateDriver'
import ChangeRequests from './components/ChangeRequests'
import ActivationPage from './components/ActivationPage'
function App() {
  const [selectedDriving, setSelectedDriving] = useState({})
  return (
    <Routes >
        <Route index path="/" element={<StartPage />} />
        <Route index path="/login" element={<Login />} />
        <Route path="/registration" element={<Registration />} />
        <Route path='/passengersDrivingHistory' element={<PassengersDrivingHistory setSelectedDriving={setSelectedDriving}/> } />
        <Route path='/driversDrivingHistory' element={<DriversDrivingHistory setSelectedDriving={setSelectedDriving}/> } />
        <Route path='/users' element={<Users /> } />
        <Route path='/edit-profile/:id' element={<EditUser /> } />
        <Route path='/edit-profile' element={<EditUser /> } />
        <Route path='/map' element={<Map /> } />
        <Route path='/passengersDrivingDetailedView' element={<PassengersDrivingDetailedView selectedDriving={selectedDriving}/> } />
        <Route path='/driversDrivingDetailedView' element={<DriversDrivingDetailedView selectedDriving={selectedDriving}/> } />
        <Route path='/passengerHomePage' element={<PassengerHomePage /> } />
        <Route path='/driverHomePage' element={<DriverHomePage /> } />
        <Route path='/adminHomePage' element={<AdminHomePage /> } />
        <Route path='/SelectedUserHistory' element={<SelectedUserHistory /> } />
        <Route path='/graphPassengers' element={<GraphPassengers /> } />
        <Route path='/graphDrivers' element={<GraphDrivers /> } />
        <Route path='/changePassword' element={<ChangePassword /> } />
        <Route path='/forgot-password' element={<ForgotPassword /> } />
        <Route path='/startPage' element={<StartPage /> } />
        <Route path='/editDriver/:id' element={<EditDriver /> } />
        <Route path='/createDriver' element={<CreateDriver /> } />
        <Route path='/requests' element={<ChangeRequests /> } />
        <Route path='/activationLink/:id' element={<ActivationPage /> } />
        
    </Routes>
  );
}
export default App;
