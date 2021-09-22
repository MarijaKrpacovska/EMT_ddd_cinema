import './App.css';
import React, {Component} from "react";
import Movies from '../Movie/movie';
import MovieDetails from '../Movie/movieDetails'
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import MovieService from "../../repository/movieRepository";
import MovieAdd from "../Movie/movieAdd";
import ScheduleMovie from "../Movie/scheduleMovie";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            movies: [],
            selectedMovie: {}
        }
    }

    render() {
        return (
                <Router>
                    <div className="container">
                        <Route path={"/movie/findById/:id"} exact render={() =>
                            <MovieDetails selectedMovie={this.state.selectedMovie}/>}/>

                        <Route path={"/movie/add"} exact render={() =>
                            <MovieAdd onAddMovie={this.addMovie}/>}/>

                        <Route path={"/movie/scheduleMovie/:id"} exact render={() =>
                            <ScheduleMovie onScheduleMovie={this.scheduleMovie}
                            movie = {this.state.selectedMovie}/>}/>

                        <Route path={"/movie"}
                               exact render={() =>
                            <Movies movies={this.state.movies}
                                    onDetails={this.getMovie}
                                    onScheduleMovie={this.getMovie}/> } />
                    </div>
                </Router>
        );
    }

    componentDidMount() {
        this.loadMovies();
    }

    loadMovies = () => {
        MovieService.fetchMovies()
            .then((data) => {
                this.setState({
                    movies: data.data
                })
            });
    }
    getMovie = (id) => {
       // console.log("vo getMovie"+id)
        MovieService.getMovie(id)
            .then((data) => {
                console.log("vo getMovie"+typeof (data.data))
                this.setState({
                    selectedMovie: data.data
                })
            })
    }
    addMovie = (name, movieLength, genre, publishDate, description,ticketPrice,scheduledMovies) => {
        MovieService.addMovie(name, movieLength, genre, publishDate, description,ticketPrice,scheduledMovies)
            .then(() => {
                this.loadMovies();
            });
    }
    scheduleMovie = (id, startTime, endTime) => {
        MovieService.scheduleMovie(id, startTime,endTime)
            .then(() => {
                this.loadMovies();
            });
    }


}

export default App;
