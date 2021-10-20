import React from 'react';
import {useHistory} from 'react-router-dom';

const ScheduledMovieAdd = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        sales : 0,
        startDate : "2021-01-01",
        startTime : "00:00",
        ticketPrice : {
            ticketPriceCurrency: "MKD",
            ticketPriceAmount: 0.0
        },
        movieId : "9a78fd3e-9caf-490a-a1d4-c91852494c05"
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const sales = 0;
        const startDate = formData.startDate;
        const startTime = formData.startTime;
        const ticketPrice = {
            "currency": "MKD",
            "amount": formData.ticketPriceAmount
        };
        const movieId= props.selectedMovie.id.id;

        props.onAddScheduledMovie(sales, startDate, startTime, ticketPrice, movieId);
        history.push("/scheduledMovies");
    }

    return(
        <div className="row mt-5">
            <div className={"col-md-6"}>
                <img src={"https://www.nicepng.com/png/full/912-9123453_cine-popcorn-cinema.png"}/>
            </div>
            <div className="col-md-6">
                <form onSubmit={onFormSubmit}>
                    
                    <div className="form-group">
                        <label htmlFor="startTime">Start time</label>
                        <input type="time"
                               className="form-control"
                               id="startTime"
                               name="startTime"
                               required
                               placeholder="Enter Movie startTime"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="startDate">Start date</label>
                        <input type="date"
                               className="form-control"
                               id="startDate"
                               name="startDate"
                               required
                               placeholder="Enter Movie startDate"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="name">TicketPriceCurrency</label>
                        <input type="text"
                               className="form-control"
                               id="ticketPriceCurrency"
                               name="ticketPriceCurrency"
                               required
                               placeholder="Enter Movie ticketPriceCurrency"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="name"> ticketPriceAmount</label>
                        <input type="number"
                               className="form-control"
                               id="ticketPriceAmount"
                               name="ticketPriceAmount"
                               required
                               placeholder="Enter Movie ticketPriceAmount"
                               onChange={handleChange}
                        />
                    </div>
                    <br/>
                    <div className={"text-center"}>
                    <button id="submit" type="submit" className="btn btn-dark">Submit</button>
                    </div>
                    </form>
            </div>
        </div>
    )
}

export default ScheduledMovieAdd;
