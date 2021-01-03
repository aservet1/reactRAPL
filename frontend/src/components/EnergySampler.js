import React from 'react';
import axios from 'axios';
import ReactFlexyTable from "react-flexy-table"
import 'react-flexy-table/dist/index.css'

class EnergySampler extends React.Component {

    constructor(props) {
        super(props)

        this.state = {
            "EnergyStats": {"energy":-1},
            "EnergyDiff": {"energy":-1},
            "EnergyDiffTime": -1,
        }

        this.requestEnergyStats = this.requestEnergyStats.bind(this)
        this.requestEnergyDiff10sec = this.requestEnergyDiff10sec.bind(this)
        this.handleTimeAmountChange = this.handleTimeAmountChange.bind(this)
    }

    requestEnergyStats(e) {
        e.preventDefault()
        axios.get("/energy")
            .then(res => {
                this.setState({"EnergyStats": res.data})
                console.log(this.state.EnergyStats)
            }).catch(err =>{
                console.log(err)
                this.setState({})
            })
    }

    requestEnergyDiff10sec(e) {
        e.preventDefault();
        this.setState({"EnergyDiff": {"Status":"loading..."}})
        axios.get("/energy10s")
            .then(res => {
                this.setState({"EnergyDiff": res.data})
                console.log(this.EnergyDiff)
            }).catch(err => {
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
            <div>
                <div className="EnergyStats">
                    <input type="button" value="EnergyStats()" onClick={this.requestEnergyStats}/>
                    <br></br>
                    <ReactFlexyTable data={[this.state.EnergyStats]}/>
                </div>

                <div className="EnergyDiff">
                    <input type="text"  onChange={this.handleTimeAmountChange}/>
                    <input type="button" value="EnergyDiff()" onClick={this.requestEnergyDiff10sec}/>
                    <br></br>
                    <ReactFlexyTable data={[this.state.EnergyDiff]} />
                </div>
            </div>
        );
    }
    

}

export default EnergySampler;