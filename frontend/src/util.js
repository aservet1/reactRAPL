export default function objectArrayToCsvString (arr) {
    const header = Object.keys(arr[0]);
    return (
        header.join(',')
        + '\n' +
        arr.map(
            (e) =>
                header.map(
                    (k) => e[k]
                ).join(',')
        ).join('\n')
    );
}