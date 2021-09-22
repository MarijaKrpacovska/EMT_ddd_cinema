import './App.css';
import React, {Component} from "react";
import Movies from '../Movie/movie';
import MovieDetails from '../Movie/movieDetails'
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import MovieService from "../../repository/movieRepository";
import MovieAdd from "../Movie/movieAdd";
import ScheduleMovie from "../Movie/scheduleMovie";
import TicketReservationAdd from "../Ticket/ticketreservationAdd";
import TicketService from "../../repository/ticketRepository";
import AddTicketToReservation from "../Ticket/addTicketToReservation";
import TicketReservationDetails from "../Ticket/ticketReservationDetails";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            movies: [],
            selectedMovie: {},
            ticketReservation: {}
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

                        <Route path={"/ticket/addTicketToReservation/:id"} exact render={() =>
                            <AddTicketToReservation onAddTicketToReservation={this.addTicketToReservation}
                                                    ticketReservation={this.state.ticketReservation}/>}/>

                        <Route path={"/ticket/getTicketReservation/:id"} exact render={() =>
                            <TicketReservationDetails ticketReservation={this.state.ticketReservation}/>}/>

                        <Route path={"/ticket/makeReservation"} exact render={() =>
                            <TicketReservationAdd onTicketReservationAdd={this.addTicketReservation}/>}/>

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
    getTicketReservation = (id) => {
        // console.log("vo getMovie"+id)
        TicketService.getTicketReservation(id)
            .then((data) => {
                console.log("vo getMovie"+typeof (data.data))
                this.setState({
                    ticketReservation: data.data
                })
            })
    }
    addMovie = (name, movieLength, genre, publishDate, description,ticketPrice,scheduledMovies) => {
        MovieService.addMovie(name, movieLength, genre, publishDate, description,ticketPrice,scheduledMovies)
            .then(() => {
                this.loadMovies();
            });
    }
    addTicketReservation = (currency,tickets) => {
        TicketService.makeReservation(currency,tickets)
            .then(() => {
                this.loadMovies();
            });
    }

    addTicketToReservation = (id,quantity,movie) => {
        TicketService.addTicketToReservation(id,quantity,movie)
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
