import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../css/table.css";

const SelectedUserHistory = (props) => {
  const token = localStorage.getItem("token");
  const [drivingHistory, setDrivingHistory] = useState([]);
  const [sortOption, setSortOption] = useState({
    field: "price",
    order: "desc",
  });
  const navigate = useNavigate();

  useEffect(() => {
    fetchDrivingHistory();
  }, []);

  const fetchDrivingHistory = async () => {
    const sortParams = {
      field: sortOption.field,
      order: sortOption.order,
    };
    const userId = localStorage.getItem("userId");
    const response = await fetch(
      `http://localhost:8080/api/drivings/usersDrivingHistory/${userId}`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
          Authorization: token,
        },
        body: JSON.stringify(sortParams),
      }
    );
    if (response.status === 400 || response.status === 401) {
      return window.alert("Wrong username or password!");
    }
    const data = await response.json();
    for (const driving of data) {
      driving.start = driving.start[2] + "." + driving.start[1] + "." + driving.start[0] + ".";
      driving.end = driving.end[2] + "." + driving.end[1] + "." + driving.end[0] + ".";
    }
    setDrivingHistory(data);
  };

  const handleSortOptionChange = (event) => {
    const value = event.target.value;
    const [field, order] = value.split("-");
    setSortOption({ field, order });
  };

  const handleSort = () => {
    fetchDrivingHistory();
  };

  return (
    <div>
      <div className="sort-container">
        <label htmlFor="sort-option">Sort By:</label>
        <select id="sort-option" value={`${sortOption.field}-${sortOption.order}`} onChange={handleSortOptionChange}>
          <option value="start-asc">Start (Ascending)</option>
          <option value="start-desc">Start (Descending)</option>
          <option value="end-asc">End (Ascending)</option>
          <option value="end-desc">End (Descending)</option>
          <option value="price-asc">Price (Ascending)</option>
          <option value="price-desc">Price (Descending)</option>
        </select>
        <button onClick={handleSort}>Sort</button>
      </div>
      <div className="game-history-container">
        <div className="game-history-table-container">
          <table className="game-history-table">
            <thead>
              <tr>
                <th>Start</th>
                <th>End</th>
                <th>Price</th>
                <th>Departure coordinates</th>
                <th>Destination coordinates</th>
              </tr>
            </thead>
            <tbody>
              {drivingHistory.map((driving) => (
                <tr key={driving.id}>
                  <td>{driving.start}</td>
                  <td>{driving.end}</td>
                  <td>{driving.price}</td>
                  <td>{driving.departure.longitude} {driving.departure.latitude}</td>
                  <td>{driving.destination.longitude} {driving.departure.latitude}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default SelectedUserHistory;