pragma solidity ^0.4.0;

contract VoteIndia{
    address public elect_gen;

    bool public votingEnded;

    bytes32[] public candidateNames;

    mapping (address => bool) public hasVoted;

    mapping (bytes32 => uint256) private votesReceived;

    uint256 public totalVotes;

    constructor(bytes32[] memory _candidateNames) public {
        elect_gen = msg.sender;
        candidateNames = _candidateNames;
    }

    function voteForCandidate(bytes32 candidate) public {
       
        require(!votingEnded);
      
        require(validCandidate(candidate));
     
        require(!hasVoted[msg.sender]);
       
        require(votesReceived[candidate] < ~uint256(0));
        require(totalVotes < ~uint256(0));

        votesReceived[candidate] += 1;
        hasVoted[msg.sender] = true;
        totalVotes += 1;
    }

    function endVoting() public returns (bool) {
        require(msg.sender == elect_gen);  
        votingEnded = true;
        return true;
    }

    function totalVotesFor(bytes32 candidate) view public returns (uint256) {
        require(validCandidate(candidate));
        require(votingEnded);  
        return votesReceived[candidate];
    }

    function numCandidates() view public returns(uint count) {
        return candidateNames.length;
    }

    function validCandidate(bytes32 candidate) view public returns (bool) {
        for(uint i = 0; i < candidateNames.length; i++) {
            if (candidateNames[i] == candidate) {
                return true;
            }
        }
        return false;
    }
}
