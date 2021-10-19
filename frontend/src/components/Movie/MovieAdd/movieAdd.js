import React from 'react';
import {useHistory} from 'react-router-dom';

//todo: fix forms
//todo: fix warnings in console
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
        url: "",
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
        const url=formData.url
        //todo: error handle data

        props.onAddMovie(name,movieLength,genre, publishDate,description,ticketPrice,url,scheduledMovies);
        history.push("/movie");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder="Enter movie name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="publishDate">Publish Date (in format: 2021-09-22T10:07:11.243557Z)</label>
                        <input type="text"
                               className="form-control"
                               id="publishDate"
                               name="publishDate"
                               placeholder="Enter movie publish date"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Description</label>
                        <input type="text"
                               className="form-control"
                               id="description"
                               name="description"
                               placeholder="Enter short movie description"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Genre</label>
                        <select name="genre" className="form-control" onChange={handleChange}>
                            <option value="action">action</option>
                            <option value="romance">romance</option>
                            <option value="horror">horror</option>
                        </select>
                    </div>

                    <div className="form-group">
                        <label htmlFor="movieLength">Length</label>
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
                        <label htmlFor="ticketPrice">Ticket Price</label>
                        <input type="number"
                               className="form-control"
                               id="ticketPrice"
                               name="ticketPrice"
                               placeholder="Enter ticket price"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="url">Movie poster url</label>
                        <input type="text"
                               className="form-control"
                               id="url"
                               name="url"
                               required
                               placeholder="Enter image url"
                               onChange={handleChange}
                        />
                    </div>


                    <button id="submit" type="submit" className="btn btn-dark align-self-md-center">Submit</button>

                    </form>
            </div>

        </div>
    )
}

export default MovieAdd;
