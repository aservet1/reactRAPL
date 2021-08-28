
import React from 'react';
import axios from 'axios';
import EnergyTable from './EnergyTable';
import 'react-flexy-table/dist/index.css'
import objectArrayToCsvString from '../util'

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

    downloadData = (e) => {
        const csvData = objectArrayToCsvString(this.state.EnergySeries);
        const element = document.createElement("a");
        const file = new Blob (
            [csvData],
            {type: 'text/csv'}
        );
        element.href = URL.createObjectURL(file);
        element.download = "energy_data.csv";
        document.body.appendChild(element); // Required for this to work in FireFox
        element.click();
    }

    render() {
        const downloadExcelProps = {
          type: 'filtered',
          title: 'test',
          showLabel: true
        }

        return (
            <div>
                <button
                    id = 'DownloadButton'
                    style = {{
                        margin: '10px'
                    }}
                    onClick = {this.downloadData}
                >
                    Download CSV Data
                </button>
                <EnergyTable
                    placeholder='duration,samplingRate'
                    onTextChange={this.handleChangeSeriesParams}
                    buttonText='EnergySeries()'
                    buttonOnClick={this.requestEnergySeries}
                    data={this.state.EnergySeries}
                />
            </div>
        );
    }
}

export default EnergySeries;
