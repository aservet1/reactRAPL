import React from 'react';
import axios from 'axios';
import ReactFlexyTable from "react-flexy-table"
import 'react-flexy-table/dist/index.css'

class EnergyStats extends React.Component {

    constructor(props) {
        super(props)

        this.state = {
            "EnergyStats": {"energy":-1},
        }

        this.requestEnergyStats = this.requestEnergyStats.bind(this)
    }

    requestEnergyStats(e) {
        e.preventDefault()
        axios.get("http://localhost:8080/energy/stats")
            .then(res => {
                this.setState({"EnergyStats": res.data})
                console.log(this.state.EnergyStats)
            }).catch(err =>{
                console.log(err)
                this.setState({})
            })
    }

    handleTimeAmountChange(e) {
        this.setState({"EnergyDiffTime" : e.target.value})
        console.log(this.state.EnergyDiffTime)
    }

    render() {
        return (
            <div className="EnergyStats">
               <input type="button" value="EnergyStats()" onClick={this.requestEnergyStats}/>
               <br></br>
               <ReactFlexyTable data={[this.state.EnergyStats]}/>
            </div>
        );
    }
}

export default EnergyStats;