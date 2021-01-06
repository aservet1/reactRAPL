
import React from 'react';
import axios from 'axios';
import ReactFlexyTable from "react-flexy-table"
import 'react-flexy-table/dist/index.css'

class EnergyDiff extends React.Component {

    constructor(props) {
        super(props)

        this.state = {
            "EnergyDiff": {"energy":-1},
            "duration": -1,
        }
    }

    requestEnergyDiff = (e) => {
        e.preventDefault()
        axios.get(`http://localhost:8080/energy/diff/${this.state.duration}`) // make it '/energy{n}msec' 
            .then(res => {
                this.setState({"EnergyDiff": res.data})
                console.log(this.state.EnergyDiff)
            }).catch(err =>{
                console.log('fail!!!!!!!!!!!!!!!!!!!!!!')
                console.log(err)
                this.setState({})
            })
    }

    handleChangeDuration = (e) => {
        this.setState({"duration" : e.target.value})
    }

    render() {
        return (
            <div>
                <div className="EnergyDiff">
                    <input type="text"  onChange={this.handleChangeDuration}/>
                    <input type="button" value="EnergyDiff()" onClick={this.requestEnergyDiff}/>
                    <br></br>
                    <ReactFlexyTable data={[this.state.EnergyDiff]} />
                </div>
            </div>
        );
    }
}

export default EnergyDiff;