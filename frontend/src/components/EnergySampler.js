import React from 'react';
import axios from 'axios';

class EnergySampler extends React.Component {

    constructor(props) {
        super(props)
        this.EnergyStats = 'null';
        this.EnergyDiff10sec = 'null';
        this.requestEnergyStats = this.requestEnergyStats.bind(this)
        this.requestEnergyDiff10sec = this.requestEnergyDiff10sec.bind(this)
    }

    requestEnergyStats(e) {
        e.preventDefault()
        axios.get("/energy")
            .then(res => {
                console.log('success')
                console.log(res.data)
                this.EnergyStats = res.data
                this.setState({})
            }).catch(err =>{
                console.log('not success')
                console.log(err)
                this.setState({})
            })
    }

    requestEnergyDiff10sec(e) {
        e.preventDefault();
        axios.get("/energy10s")
            .then(res => {
                console.log('success')
                console.log(res.data)
                this.EnergyDiff10sec = res.data;
                this.setState({})
            })
    }

    render() {
        return (
            <div>
            <div className="EnergyStats">
                <input type="button" value="getEnergy()" onClick={this.requestEnergyStats}/>
                <br></br>
                {this.EnergyStats}
            </div>

            <div className="EnergyDiff10sec">
                <input type="button" value="getEnergy()" onClick={this.requestEnergyDiff10sec}/>
                <br></br>
                {this.EnergyDiff10sec}
            </div>
            </div>
        );
    }
    

}

export default EnergySampler;