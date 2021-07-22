
import React from 'react';
import axios from 'axios';
import EnergyTable from './EnergyTable';
import 'react-flexy-table/dist/index.css'

class EnergySeries extends React.Component {


    PLACEHOLDER = [{"energy series": "~~~~~~~~~~~~~~~~~"}];
    WAITING = [{'fetching':'...'}]

    constructor(props) {
        super(props)

        this.state = {
            "EnergySeries": this.PLACEHOLDER,
            "duration": 0,
            "samplingRate": 0
        }
    }

    requestEnergySeries = (e) => {
        e.preventDefault()
    
        this.setState({EnergySeries: this.WAITING})

        const d = this.state.duration;
        const s = this.state.samplingRate;
        axios.get(`http://localhost:8080/energy/diff/list?duration=${d}&sampling_rate=${s}`)
            .then(res => {
                this.setState({"EnergySeries": res.data})
            }).catch(err =>{
                console.log('fail!!!!!!!!!!')
                console.log("err:",err)
                this.setState({})
            })
    }

    handleChangeSeriesParams = (e) => {
        const params = e.target.value.split(",");
        this.setState({
            "duration" : params[0] ,
            "samplingRate" : params[1]
        });
    }

    render() {
        const downloadExcelProps = {
          type: 'filtered',
          title: 'test',
          showLabel: true
        }

        return (
            <EnergyTable
                placeholder='duration,samplingRate'
                onTextChange={this.handleChangeSeriesParams}
                buttonText='EnergySeries()'
                buttonOnClick={this.requestEnergySeries}
                data={this.state.EnergySeries}
            />
        );
    }
}

export default EnergySeries;
