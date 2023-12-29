import React from 'react';

const SearchBar = ({ onSubmit }) => {
	const handleSubmit = (e) => {
		e.preventDefault();
		onSubmit(e.target.elements.filter.value);
	};

	return (
		<form onSubmit={handleSubmit}>
			<input name="filter" />
			<button>검색</button>
		</form>
	);
};

export default SearchBar;