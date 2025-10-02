const fs = require('node:fs/promises');
const path = require('node:path');

/**
 * Upload all files in a directory to a release.
 *
 * @param {object} param0 The GitHub context object.
 * @param {string} artifactDirectory The path to the directory containing the
 * artifacts to upload.
 * @param {string} releaseId The ID of the release to upload artifacts to.
 *
 * @returns {Promise<void>} A promise that resolves when all assets have been
 * uploaded.
 */
module.exports = async (
    {github, context, core},
    artifactDirectory,
    releaseId
) => {
    const {owner, repo} = context.repo;
    const assets = await fs.readdir(artifactDirectory, {
        encoding: 'utf-8',
        recursive: true
    });
    core.info(`Uploading ${assets.length} assets to release ${releaseId}...`);
    for (const artifactPath of assets) {
        core.info(`Uploading ${artifactPath}...`);
        let artifactName = path.basename(artifact);
        core.info(`Reading ${artifactName} from ${artifactPath}...`);
        const data = await fs.readFile(artifactPath);
        core.info(`Uploading to release as ${artifactName}...`);
        await github.rest.repos.uploadReleaseAsset({
            owner,
            repo,
            release_id: releaseId,
            name: artifactName,
            data
        });
    }
    core.info('All release assets uploaded!');
};
