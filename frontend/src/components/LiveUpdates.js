
import React from 'react';
import axios from 'axios';
import { Line } from "react-chartjs-2";

class LiveUpdates extends React.Component {

    constructor(props) {
        super(props)

        this.samples = []
        this.timestamps = [Date.now()]
        this.on = false;

        this.toggle = this.toggle.bind(this);
        this.sleep = this.sleep.bind(this);
    }

    sleep(ms) {
       return new Promise(resolve => setTimeout(resolve, ms));
    }

    async toggle(e) {
        e.preventDefault();
 
        this.on = !(this.on)

        while(this.on) {

            axios.get(`http://localhost:8080/energy/diff/1000`)
            .then(
                res => {
                    this.samples.push(res.data);
                    this.timestamps.push(Date.now() - this.timestamps[0])
                }
            ).catch(
                err => {
                    console.log('error!!')
                    console.log(err);
                }
            );

            await this.sleep(1000)
        }
    }

    render() {
        
        return (
            <div>
                <div className="LiveUpdates">
                    <input type="button" value="Start/Stop" onClick={this.toggle}/>
                    <br></br>
                    <div>
                        <Line
                            data={this.samples}
                            options={{
                                responsive: true,
                                title: { text: "Energy Alive", display: true },
                                scales: {
                                yAxes: [ {
                                        ticks: {
                                            autoSkip: true,
                                            maxTicksLimit: 10,
                                            beginAtZero: true
                                        },
                                        gridLines: { display: false }
                                    } ],
                                xAxes: [ { gridLines: { display: false } } ]
                                }
                            }}
                        />
                    </div>
                </div>
            </div>
        );
    }

}

export default LiveUpdates;
