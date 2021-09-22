import React from 'react';
import {useHistory} from 'react-router-dom';

const MovieAdd = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        name: "",
        movieLength: {
            length: 0.0,
            unitOfTime: "min"
        },
        genre: "action",
        publishDate: "2021-09-22T10:07:11.243557Z",
        description: "desc",
        ticketPrice: {
            currency: "MKD",
            amount: 0.0
        },
        scheduledMovies: []
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name;
        const publishDate = formData.publishDate;
        const description = formData.description;
        const movieLength = {
                length: formData.movieLength,
                unitOfTime: "min"
            };
        const genre= formData.genre;

        const ticketPrice= {
            currency: "MKD",
                amount: formData.ticketPrice
        };
        const scheduledMovies= []

        props.onAddMovie(name,movieLength,genre, publishDate,description,ticketPrice,scheduledMovies);
        history.push("/movie");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Movie name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder="Enter Movie name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="publishDate">Publish date</label>
                        <input type="text"
                               className="form-control"
                               id="publishDate"
                               name="publishDate"
                               placeholder="publishDate"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">description</label>
                        <input type="text"
                               className="form-control"
                               id="description"
                               name="description"
                               placeholder="description"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>genre</label>
                        <select name="genre" className="form-control" onChange={handleChange}>
                            <option value="action">action</option>
                            <option value="romance">romance</option>
                            <option value="horror">horror</option>
                        </select>
                    </div>

                    <div className="form-group">
                        <label htmlFor="movieLength">Movie Length</label>
                        <input type="number"
                               className="form-control"
                               id="movieLength"
                               name="movieLength"
                               placeholder="[min]"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="ticketPrice">Price</label>
                        <input type="number"
                               className="form-control"
                               id="ticketPrice"
                               name="ticketPrice"
                               placeholder="ticketPrice"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default MovieAdd;
