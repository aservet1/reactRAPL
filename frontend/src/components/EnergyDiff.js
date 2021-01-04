
import React from 'react';
import axios from 'axios';
import ReactFlexyTable from "react-flexy-table"
import 'react-flexy-table/dist/index.css'

class EnergyDiff extends React.Component {

    constructor(props) {
        super(props)

        this.state = {
            "EnergyDiff": {"energy":-1},
            "delay": -1,
        }

        this.requestEnergyDiff = this.requestEnergyDiff.bind(this) // make it take a parameter of n ms to delay over
        this.handleTimeAmountChange = this.handleTimeAmountChange.bind(this)
    }

    requestEnergyDiff(e) {
        e.preventDefault()
        axios.get(`/energy/diff/${this.state.delay}`) // make it '/energy{n}msec' 
            .then(res => {
                this.setState({"EnergyDiff": res.data})
                console.log(this.state.EnergyDiff)
            }).catch(err =>{
                console.log('fail!!!!!!!!!!!!!!!!!!!!!!')
                console.log(err)
                this.setState({})
            })
    }

    handleTimeAmountChange(e) {
        this.setState({"delay" : e.target.value})
        console.log(this.state.delay)
    }

    render() {
        return (
            <div>
                <div className="EnergyDiff">
                    <input type="text"  onChange={this.handleTimeAmountChange}/>
                    <input type="button" value="EnergyDiff()" onClick={this.requestEnergyDiff}/>
                    <br></br>
                    <ReactFlexyTable data={[this.state.EnergyDiff]} />
                </div>
            </div>
        );
    }
}

export default EnergyDiff;