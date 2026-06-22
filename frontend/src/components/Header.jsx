function Header({ title, description }) {
    return (
        <>
            <h1>{title}</h1>
            {description && <p>{description}</p>}
        </>
    );
}

export default Header;