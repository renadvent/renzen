'use strict';

// tag::vars[]
const React = require('react'); // <1>
const ReactDOM = require('react-dom'); // <2>
const client = require('./client'); // <3>
// end::vars[]

const follow = require('./follow');
const root = '/api';

//-----------------------------------------------------------------------



class testNote extends React.Component {

	constructor (props)  {
		super(props);
		this.state.content=props.content;
		this.state.user=props.user;
		this.onCreate=this.onCreate.bind(this);
	}

	//onCreate

	// tag::follow-1[]
	componentDidMount() {

		//fetch ()
		//this.loadFromServer(this.state.pageSize);
	}

	loadFromServer() {

	}
}

//-----------------------------------------------------------------------

// tag::app[]
class App extends React.Component { // <1>

	constructor(props) {
		super(props);
		this.state = {employees: []};
	}

	componentDidMount() { // <2>

	console.log(client)

		this.loadFromServer(this.state.pageSize);

//	    const response = client({method: 'GET', path: '/api/employees'});
//
//	    console.log(response)
//
//        response.then()
	    //response.done( () => {return})

	    //this.setState({employees:response.entity._embedded.employees});

//		client({method: 'GET', path: '/api/employees'}).done(response => {
//			this.setState({employees: response.entity._embedded.employees});
//		});

		// client({method: 'GET', path: '/api/employees'}).then(response => {
		// 	this.setState({employees: response.entity._embedded.employees});
		// });
	}

	loadFromServer(pageSize) {
		follow(client, root, [
			{rel: 'employees', params: {size: pageSize}}]
		).then(employeeCollection => {
			return client({
				method: 'GET',
				path: employeeCollection.entity._links.profile.href,
				headers: {'Accept': 'application/schema+json'}
			}).then(schema => {
				this.schema = schema.entity;
				return employeeCollection;
			});
		}).done(employeeCollection => {
			this.setState({
				employees: employeeCollection.entity._embedded.employees,
				attributes: Object.keys(this.schema.properties),
				pageSize: pageSize,
				links: employeeCollection.entity._links});
		});
	}

	render() { // <3>
		return (
			<EmployeeList employees={this.state.employees}/>
		)
	}
}
// end::app[]

// tag::employee-list[]
class EmployeeList extends React.Component{
	render() {
		const employees = this.props.employees.map(employee =>
			<Employee key={employee._links.self.href} employee={employee}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Description</th>
					</tr>
					{employees}
				</tbody>
			</table>
		)
	}
}
// end::employee-list[]

// tag::employee[]
class Employee extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.employee.firstName}</td>
				<td>{this.props.employee.lastName}</td>
				<td>{this.props.employee.description}</td>
			</tr>
		)
	}
}
// end::employee[]

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
// end::render[]
