import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../css/form.css";
import { Chart } from "react-google-charts";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

const GraphPassengers = (props) => {
  const token = localStorage.getItem("token");
  const today = new Date().toLocaleDateString();
  const [drivingStatistic, setDrivingStatistic] = useState([]);
  const [range, setRange] = useState({
    start: "2023-08-22",
    end: "2023-08-22"
  });

  const [numberOfRidesData, setNumberOfRidesData] = useState([
    ["Element", "NumberOfRides"],
  ]);
  const [kilometersTravelledData, setKilometersTravelledData] = useState([
    ["Element", "KilometersTravelled"],
  ]);
  const [spentMoneyData, setSpentMoneyData] = useState([
    ["Element", "MoneySpent"],
  ]);
  const navigate = useNavigate();
  const data = [
    ["Element", "NumberOfdays"],
    ["Copper", 8.94], // RGB value
    ["Silver", 10.49], // English color name
    ["Gold", 19.3],
    ["Platinum", 21.45], // CSS-style declaration
  ];

  useEffect(() => {
    fetchDrivingStatistic();
  }, []);
  
  const handleStartDateChange = (date) => {
    setRange((prevRange) => ({
      ...prevRange,
      start: date.toISOString().split("T")[0],
    }));
  };

  const handleEndDateChange = (date) => {
    setRange((prevRange) => ({
      ...prevRange,
      end: date.toISOString().split("T")[0],
    }));
  };

  const handleSubmit = () => {
    if (range.start && range.end) {
      fetchDrivingStatistic();
    } else {
      window.alert("Please select both start and end dates.");
    }
  };

  const[totalNumberOfDrivings, setTotalNumberOfDrivings] = useState(0);
  const[totalKilometersTravelled, setTotalKilometersTravelled] = useState(0);
  const [totalSpentMoney, setTotalSpentMoney] = useState(0);


  const fetchDrivingStatistic = async () => {
    const rangeParams = {
      start: range.start,
      end: range.end,
    };

    let totalNumberOfDrivings = 0;
    let totalKilometersTravelled = 0;
    let totalSpentMoney = 0;

    numberOfRidesData.splice(1); 
    kilometersTravelledData.splice(1); 
    spentMoneyData.splice(1);

    const response = await fetch(
      "http://localhost:8080/api/passengers/getStatistics",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
          Authorization: token,
        },
        body: JSON.stringify(rangeParams),
      }
    );
    if (response.status === 400 || response.status === 401) {
      return window.alert("Wrong username or password!");
    }
    const data = await response.json();
    for (const driving of data) {
      const formattedDate =
        driving.date[2] + "." + driving.date[1] + "." + driving.date[0] + ".";

      numberOfRidesData.push([formattedDate, driving.numberOfDrivings]);
      totalNumberOfDrivings += driving.numberOfDrivings;

      kilometersTravelledData.push([
        formattedDate,
        driving.kilometersTravelled,
      ]);
      totalKilometersTravelled += driving.kilometersTravelled;

      spentMoneyData.push([formattedDate, driving.money]);
      totalSpentMoney += driving.money;
    }

    setTotalNumberOfDrivings(totalNumberOfDrivings);
    setTotalKilometersTravelled(totalKilometersTravelled);
    setTotalSpentMoney(totalSpentMoney);
    
    
    setDrivingStatistic(data);
  };

  return (
    <div>
      <div className="datepicker-row">
        <div className="datepicker-label">
          <label>Start Date: </label>
        </div>
        <div className="datepicker-input">
          <DatePicker
            selected={new Date(range.start)}
            onChange={handleStartDateChange}
            dateFormat="dd.MM.yyyy."
          />
        </div>
        <div className="datepicker-label">
          <label>End Date: </label>
        </div>
        <div className="datepicker-input">
          <DatePicker
            selected={new Date(range.end)}
            onChange={handleEndDateChange}
            dateFormat="dd.MM.yyyy."
          />
        </div>
        <button onClick={handleSubmit}>Submit</button>
      </div>
      <div className="graph">
        <label>Number of drivings: </label>
        <Chart
          chartType="ColumnChart"
          width="90%"
          height="300px"
          data={numberOfRidesData}
        />
        <div className="info">
          <span className="label-space"></span>
          <label>Total drivings: {totalNumberOfDrivings}</label>
        </div>
        <label>Kilometers travelled: </label>
        <Chart
          chartType="ColumnChart"
          width="90%"
          height="300px"
          data={kilometersTravelledData}
        />
        <div className="info">
          <span className="label-space"></span>
          <label>Total kilometers: {totalKilometersTravelled}</label>
        </div>
        <label>Spent money: </label>
        <Chart
          chartType="ColumnChart"
          width="90%"
          height="300px"
          data={spentMoneyData}
        />
        <div className="info">
          <span className="label-space"></span>
          <label>Sum: {totalSpentMoney}</label>
        </div>
      </div>
    </div>
  );
};

export default GraphPassengers;
