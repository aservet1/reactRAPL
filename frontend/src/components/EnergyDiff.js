
import React from 'react';
import axios from 'axios';
import EnergyTable from './EnergyTable';
import 'react-flexy-table/dist/index.css'
import '../App.css'

class EnergyDiff extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            "EnergyDiff": {"energy":-1},
            "duration": -1,
        };
    }

    requestEnergyDiff = (e) => {
        e.preventDefault()
        axios.get(`http://localhost:8080/energy/diff/sample?duration=${this.state.duration}`)
            .then(res => {
                this.setState({
                    "EnergyDiff": res.data
                });
                // console.log(this.state.EnergyDiff);
            }).catch(err =>{
                this.setState({
                    EnergyDiff: {
                        "error": "there was an error in the GET request"
                    }
                });
                // console.log(err);
            })
    }

    handleChangeDuration = (e) => {
        this.setState({
            "duration" : e.target.value
        });
    }

    render() {
        return (
            <EnergyTable
                placeholder    =  'duration'
                onTextChange   =  {this.handleChangeDuration}
                buttonText     =  'EnergyDiff()'
                buttonOnClick  =  {this.requestEnergyDiff}
                data           =  {[this.state.EnergyDiff]}
            />
        );
    }
}

export default EnergyDiff;
